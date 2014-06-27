package com.cyeam.cyeamdroid.wallpaper;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.cyeam.cyeamdroid.app.CyeamApplication;
import com.cyeam.cyeamdroid.app.R;
import com.cyeam.cyeamdroid.app.Receiver;
import com.cyeam.cyeamdroid.http.CyeamHttp;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Bing {

    private static final String Bing_URL = "http://www.bing.com/HPImageArchive.aspx?format=json&idx=0&n=1";

    public static void SetWallpaper() {
        GetBing();
    }

    public static void GetBing() {
        CyeamHttp.get(Bing_URL, null, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  String responseBody, Throwable error) {
                // TODO Auto-generated method stub
                super.onFailure(statusCode, headers, responseBody, error);
            }

            @Override
            public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
                com.cyeam.cyeamdroid.model.Bing bing = null;

                DocumentBuilderFactory factory = null;
                DocumentBuilder builder = null;
                Document document = null;
                InputStream inputStream = null;
                factory = DocumentBuilderFactory.newInstance();

                try {
                    builder = factory.newDocumentBuilder();
                    inputStream = new ByteArrayInputStream(arg2);
                    document = builder.parse(inputStream);
                    Element root = document.getDocumentElement();
                    NodeList nodes = root
                            .getElementsByTagName(com.cyeam.cyeamdroid.model.Bing.IMAGE);

                    if (nodes.getLength() > 0) {
                        bing = new com.cyeam.cyeamdroid.model.Bing();
                        Element bingEle = (Element) (nodes.item(0));

                        Element startDate = (Element) bingEle
                                .getElementsByTagName(
                                        com.cyeam.cyeamdroid.model.Bing.STARTDATE)
                                .item(0);
                        bing.setStartDate(startDate.getFirstChild()
                                .getNodeValue());

                        Element fullStartDate = (Element) bingEle
                                .getElementsByTagName(
                                        com.cyeam.cyeamdroid.model.Bing.FULLSTARTDATE)
                                .item(0);
                        bing.setFullstartdate(fullStartDate.getFirstChild()
                                .getNodeValue());

                        Element endDate = (Element) bingEle
                                .getElementsByTagName(
                                        com.cyeam.cyeamdroid.model.Bing.ENDDATE)
                                .item(0);
                        bing.setEnddate(endDate.getFirstChild().getNodeValue());

                        Element url = (Element) bingEle.getElementsByTagName(
                                com.cyeam.cyeamdroid.model.Bing.URL).item(0);
                        bing.setUrl(com.cyeam.cyeamdroid.model.Bing.BING_BASE
                                + url.getFirstChild().getNodeValue());

                        Element urlBase = (Element) bingEle
                                .getElementsByTagName(
                                        com.cyeam.cyeamdroid.model.Bing.URLBASE)
                                .item(0);
                        bing.setUrlBase(urlBase.getFirstChild().getNodeValue());

                        Element copyright = (Element) bingEle
                                .getElementsByTagName(
                                        com.cyeam.cyeamdroid.model.Bing.COPYRIGHT)
                                .item(0);
                        bing.setCopyright(copyright.getFirstChild()
                                .getNodeValue());

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
                } finally {
                    SetWallpaper(bing);
                }

            }

        });
    }

    public static void SetWallpaper(final com.cyeam.cyeamdroid.model.Bing bing) {
        System.out.println("********" + bing.getStartDate()
                + bing.getFullstartdate() + bing.getEnddate() + bing.getUrl()
                + bing.getUrlBase() + bing.getCopyright());

        CyeamHttp.get(bing.getUrl(), null, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  byte[] binaryData, Throwable error) {
                // TODO Auto-generated method stub
                super.onFailure(statusCode, headers, binaryData, error);
            }

            @Override
            public void onSuccess(int arg0, Header[] arg1, byte[] binaryData) {
                WallpaperManager wallpaperManager = WallpaperManager
                        .getInstance(CyeamApplication.getInstance());

                Bitmap bitmap = BitmapFactory.decodeByteArray(binaryData, 0,
                        binaryData.length);
                try {
                    wallpaperManager.setBitmap(bitmap);
                    Receiver.onText(Receiver.WALLPAPER_NOTIFICATION, CyeamApplication.getInstance().getResources().getString(R.string.wallpaper_success), bing.getCopyright(), bing.getFullstartdate());
//					Toast.makeText(CyeamApplication.getInstance(), CyeamApplication.getInstance().getResources().getString(R.string.wallpaper_success), Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        });
    }
}
