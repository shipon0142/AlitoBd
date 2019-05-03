package com.example.alitobd;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    WebView aliToBdWV;
    TextView titleTV, discountPriceTV, priceTV, discountRateTv, totalOrderTv;
    TextView productSizeTV;
    TextView shippingChargeTV;
    ImageView imageIV;
    EditText urlEt;
    RecyclerView sizeRV;
    ArrayList<String> sizes = new ArrayList<>();
    String productName, price, discountPrice, discountRate, totalOrder, productSize, shippingCharge;
    String url = "https://www.aliexpress.com/item/Men-s-Quick-Dry-Cycling-Jerseys-Bib-Sets-MTB-Bike-Bicycle-Short-Sleeve-Clothes-Breathable-Summer/32904628339.html?spm=2114.search0203.3.3.705e1a971vsWqj&s=p&ws_ab_test=searchweb0_0,searchweb201602_3_10065_10068_319_10059_10884_317_10887_10696_321_322_10084_453_10083_454_10103_10618_10307_537_536_10902,searchweb201603_35,ppcSwitch_0&algo_expid=1ad3acbb-a05f-40cb-a89b-ea7d6ecbeb35-0&algo_pvid=1ad3acbb-a05f-40cb-a89b-ea7d6ecbeb35";
    String url1 = "https://www.aliexpress.com/item/DOOGEE-Y8-Android-9-0-FDD-LTE-6-1inch-19-9-Waterdrop-LTPS-Screen-Smartphone-MTK6739/32968339738.html?spm=2114.search0203.3.1.5da9eb82UwpjYJ&ws_ab_test=searchweb0_0,searchweb201602_3_10065_10068_319_10059_10884_317_10887_10696_321_322_10084_453_10083_454_10103_10618_10307_537_536_10902,searchweb201603_6,ppcSwitch_0&algo_expid=c384e5db-5ee1-4079-ae32-97d98ab22349-0&algo_pvid=c384e5db-5ee1-4079-ae32-97d98ab22349";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titleTV = findViewById(R.id.TitleTV);
        imageIV=findViewById(R.id.ImageIV);
        discountPriceTV = findViewById(R.id.DiscountPriceTv);
        priceTV = findViewById(R.id.PriceTV);
        discountRateTv = findViewById(R.id.DiscountRateTv);
        totalOrderTv = findViewById(R.id.TotalOrderTv);
        urlEt = findViewById(R.id.UrlET);
        productSizeTV = findViewById(R.id.ProductSizeTV);
        sizeRV = findViewById(R.id.SizeRV);
        shippingChargeTV = findViewById(R.id.ShippingChargeTV);

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                handleSendText(intent); // Handle text being sent
            } else if (type.startsWith("image/")) {
                handleSendImage(intent); // Handle single image being sent
            }
        } else if (Intent.ACTION_SEND_MULTIPLE.equals(action) && type != null) {
            if (type.startsWith("image/")) {
                handleSendMultipleImages(intent); // Handle multiple images being sent
            }
        } else {
            // Handle other intents, such as being started from the home screen
        }


    }

    void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
  int index=sharedText.lastIndexOf("/");
  String substring=sharedText.substring(index);
           url="https://s.click.aliexpress.com/e"+substring;
           // titleTV.setText(sharedText);
              new Description().execute();
            // Update UI to reflect text being shared
        } else {
            //      new Description().execute();
        }
    }

    void handleSendImage(Intent intent) {
        Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (imageUri != null) {
           // Picasso.get().load(imageUri).into(imageIV);

            // Update UI to reflect image being shared
        }
    }

    void handleSendMultipleImages(Intent intent) {
        ArrayList<Uri> imageUris = intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
        if (imageUris != null) {
            // Update UI to reflect multiple images being shared
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {
        if (aliToBdWV.canGoBack()) {
            aliToBdWV.goBack();
        } else {
            super.onBackPressed();
        }
    }

    public void OnClickSync(View view) {
        //url1=urlEt.getText().toString();

    }

    private class Description extends AsyncTask<Void, Void, Void> {
        String desc;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                // Document mBlogDocument = Jsoup.connect(url1).userAgent("Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1667.0 Safari/537.36").get();
                //  System.out.println(html);
                Document mBlogDocument = Jsoup.connect(url)
                        .userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36")
                        .referrer("http://www.google.com")
                        .get();
                Elements title = mBlogDocument.select("div[class=detail-wrap]").select("h1");
                Elements discountPricee = mBlogDocument.select("div[class=p-price-content]").select("span[class=p-price]");
                Elements pricee = mBlogDocument.select("div[class=p-del-price-detail]").select("del[class=p-del-price-content notranslate]").select("span[class=p-price]");
                Element shippingId = mBlogDocument.getElementById("j-product-shipping");
                Elements shippingCharges = shippingId.select("dd[class=p-item-main]")
                        .select("div[class=p-logistics-detail util-clearfix]").select("span[class=logistics-cost]");
                //Element shippingCharges=mBlogDocument.getElementById("j-product-operate-wrap");
                Elements discountRates = mBlogDocument.select("div[class=p-current-price]").select("span[class=p-discount-rate]");
                Elements totalOrders = mBlogDocument.select("div[class=product-star-order util-clearfix]").select("span[class=order-num]");
                Elements productSizess = mBlogDocument.select("div[class=j-product-info-sku]").select("dl[class=p-property-item]").select("dd[class=p-item-main]")
                        .select("ul[class=sku-attr-list util-clearfix]").select("li").select("a").select("span");
                Element productSizesss = mBlogDocument.getElementById("j-sku-list-2");
                Elements links = productSizesss.getElementsByTag("li");

                productName = title.text().toString();
                discountPrice = discountPricee.text().toString();
                price = pricee.text().toString();
                discountRate = discountRates.text().toString();
                totalOrder = totalOrders.text().toString();
                productSize = String.valueOf(links.size());
                shippingCharge = shippingCharges.text().toString();
                sizes.clear();
                for (int i = 0; i < links.size(); i++) {
                    sizes.add(links.get(i).getElementsByTag("span").text());
                }

            } catch (IOException e) {
                String r = "Exception";
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            titleTV.setText("Title: " + productName);
            discountPriceTV.setText("Discount price: " + discountPrice);
            priceTV.setText("Price: " + price);
            discountRateTv.setText("Discount rate: " + discountRate);
            totalOrderTv.setText("Total orders: " + totalOrder);

            productSizeTV.setText("Sizes");
            SizeAdopter cartAdopter = new SizeAdopter(MainActivity.this, sizes);
            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
            sizeRV.setLayoutManager(layoutManager);
            sizeRV.setItemAnimator(new DefaultItemAnimator());
            sizeRV.setAdapter(cartAdopter);
            String locale = MainActivity.this.getResources().getConfiguration().locale.getCountry();
            shippingChargeTV.setText("Shipping Charge" + shippingCharge);
        }
    }
}

