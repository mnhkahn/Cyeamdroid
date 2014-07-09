package com.cyeam.cyeamdroid.app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cyeam.cyeamdroid.http.CyeamHttp;
import com.cyeam.cyeamdroid.model.Blog;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by bryce on 14-6-25.
 */
public class BlogFragment extends ListFragment {

    private BlogAdatper adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blog, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
    }

    public void setAdapter(BlogAdatper adapter) {
        this.setListAdapter(adapter);

    }

    private void getData() {
        final List<Blog> blogs = new ArrayList<Blog>();

        CyeamHttp.get("http://blog.cyeam.com/rss.xml", null, new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseBody) {
                super.onSuccess(statusCode, headers, responseBody);
                Log.e("******************", "s" + statusCode);
            }

            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                Log.e("******************", "s" + 1);
            }

            @Override
            public void onSuccess(int statusCode, String content) {
                super.onSuccess(statusCode, content);
                Log.e("******************", "s" + statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                super.onSuccess(statusCode, headers, responseBody);
//                System.out.println(responseBody); 
                DocumentBuilderFactory factory = null;
                DocumentBuilder builder = null;
                Document document = null;
                InputStream inputStream = null;
                factory = DocumentBuilderFactory.newInstance();

                try {
                    builder = factory.newDocumentBuilder();
                    inputStream = new ByteArrayInputStream(responseBody);
                    document = builder.parse(inputStream);
                    Element root = document.getDocumentElement();
                    NodeList nodes = root
                            .getElementsByTagName(Blog.Item);

                    Log.e("******************", "" + nodes.getLength());
                    for (int i = 0; i < nodes.getLength(); i++){
                        Blog blog = new Blog();
                        Element ele = (Element) (nodes.item(i));

                        Element title = (Element) ele
                                .getElementsByTagName(Blog.Title).item(0);
                        blog.setTitle(title.getFirstChild().getNodeValue());

                        Element figure = (Element) ele
                                .getElementsByTagName(
                                        Blog.Figure)
                                .item(0);
                        if (figure.getFirstChild() != null) {
                            blog.setFigure(figure.getFirstChild()
                                    .getNodeValue());
                        }

                        Element info = (Element)ele.getElementsByTagName(Blog.Info).item(0);
                        if (info.getFirstChild() != null) {
                            blog.setInfo(info.getFirstChild().getNodeValue());
                        }

                        Element description = (Element) ele
                                .getElementsByTagName(
                                        Blog.Description)
                                .item(0);
                        blog.setDescription(description.getFirstChild().getNodeValue());

                        Element link = (Element) ele.getElementsByTagName(
                                Blog.Link).item(0);
                        blog.setLink(link.getFirstChild().getNodeValue());

                        Element pubDate = (Element) ele
                                .getElementsByTagName(
                                        Blog.PubDate)
                                .item(0);
                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            blog.setPubDate(format.parse(pubDate.getFirstChild().getNodeValue().substring(0, pubDate.getFirstChild().getNodeValue().indexOf("T"))));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        blogs.add(blog);
                    }
                } catch (ParserConfigurationException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (SAXException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                adapter = new BlogAdatper(getActivity(), R.layout.blog, blogs);

                setAdapter(adapter);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseBody, Throwable error) {
                super.onFailure(statusCode, headers, responseBody, error);
                Log.e("******************", "" + statusCode);
            }

            @Override
            public void onFailure(String responseBody, Throwable error) {
                super.onFailure(responseBody, error);
                Log.e("******************", "" + 1);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                super.onFailure(statusCode, headers, responseBody, error);
                Log.e("******************", "" + statusCode);
            }

            @Override
            public void onFailure(Throwable error) {
                super.onFailure(error);
                Log.e("******************", "" + 2);
            }

            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
                Log.e("******************", "" + 3);
            }

            @Override
            public void onFailure(int statusCode, Throwable error, String content) {
                super.onFailure(statusCode, error, content);
                Log.e("******************", "" + statusCode);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable error, String content) {
                super.onFailure(statusCode, headers, error, content);
                Log.e("******************", "" + statusCode);
            }
        });
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Blog blog = (Blog) l.getItemAtPosition(position);

        Intent postIntent = new Intent(getActivity(), PostActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(Blog.BLOG_SER, blog);
        postIntent.putExtras(mBundle);
        startActivity(postIntent);
    }
}
