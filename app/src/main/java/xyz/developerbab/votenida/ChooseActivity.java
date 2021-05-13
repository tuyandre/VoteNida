package xyz.developerbab.votenida;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import xyz.developerbab.votenida.Adapter.ViewdistrictAdapter;
import xyz.developerbab.votenida.Adapter.ViewprovinceAdapter;
import xyz.developerbab.votenida.Model.District;
import xyz.developerbab.votenida.Model.Province;
import xyz.developerbab.votenida.interfaces.RESTApiInterface;
import xyz.developerbab.votenida.singleton.RESTApiClient;

public class ChooseActivity extends AppCompatActivity implements View.OnClickListener, ViewdistrictAdapter.OnItemClickListener {
    private Button btncontinuechoose;
    private Button btnchooseprovince, btnchoosedistrict;
    private RecyclerView recyclerViewprovince, recyclerViewdistrict;
    private TextView tvshowprovince, tvshowdistrict;
    private String jsonresponse, jsonresponse2,district,province;
    private ProgressBar pbprovince, pbdistrict;


    private ArrayList<Province> list_data;
    private ArrayList<District> list_data2;

    private TextView tvgotovote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);


        btnchooseprovince = findViewById(R.id.btnchooseprovince);
        btnchooseprovince.setOnClickListener(this);
        recyclerViewprovince = findViewById(R.id.recyclerviewprovince);
        tvshowprovince = findViewById(R.id.tvshowprovince);
        pbprovince = findViewById(R.id.pbprovince);


        btnchoosedistrict = findViewById(R.id.btnchoosedistrict);
        btnchoosedistrict.setOnClickListener(this);
        recyclerViewdistrict = findViewById(R.id.recyclerviewdistrict);
        tvshowdistrict = findViewById(R.id.tvshowdistrict);
        pbdistrict = findViewById(R.id.pbpdistict);


        btncontinuechoose = findViewById(R.id.btncontinuechoose);
        btncontinuechoose.setOnClickListener(this);

        pbprovince.setVisibility(View.GONE);
        pbdistrict.setVisibility(View.GONE);

        recyclerViewprovince.setHasFixedSize(true);
        recyclerViewprovince.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        list_data = new ArrayList<>();

        recyclerViewdistrict.setHasFixedSize(true);
        recyclerViewdistrict.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        list_data2= new ArrayList<>();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btncontinuechoose:
                if (tvshowdistrict.getText().toString().isEmpty()||tvshowprovince.getText().toString().isEmpty()){
                    Toast.makeText(this, "Please first ,choose both your province and your district", Toast.LENGTH_LONG).show();
                }else {
                    Intent intent=new Intent(ChooseActivity.this,MainActivity.class);
                    intent.putExtra("province",province);
                    intent.putExtra("district",district);
                    startActivity(intent);

                }

                break;

            case R.id.btnchooseprovince:
                pbprovince.setVisibility(View.VISIBLE);
                getprovince();
                break;

            case R.id.btnchoosedistrict:
                if (tvshowprovince.getText().toString().isEmpty()){
                    Toast.makeText(this, "Please,First choose your province", Toast.LENGTH_SHORT).show();
                }else {

                    pbdistrict.setVisibility(View.VISIBLE);
                    getdistrict();
                }
                break;
        }

    }

    private void getprovince() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://nida.developerbab.xyz/api/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        RESTApiInterface api = retrofit.create(RESTApiInterface.class);

        Call<String> call = api.getprovince();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        jsonresponse = response.body();

                        //      swipergetdoc.setVisibility(View.GONE);

                        displayprov();


                        //        Toast.makeText(MainActivity.this, "okay "+response.body(), Toast.LENGTH_LONG).show();

                        pbprovince.setVisibility(View.GONE);

                    } else {

                        pbprovince.setVisibility(View.GONE);
                        Toast.makeText(ChooseActivity.this, "No data is found", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                pbprovince.setVisibility(View.GONE);
                Toast.makeText(ChooseActivity.this, t.getCause().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void displayprov() {
        try {
            JSONObject object = new JSONObject(jsonresponse);
            JSONArray array = object.getJSONArray("province");
            for (int i = 0; i < array.length(); i++) {

                JSONObject jsonObject = array.getJSONObject(i);


                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");


                Province model = new Province();
                model.setId(id);
                model.setProvince(name);

                list_data.add(model);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ViewdistrictAdapter adapter = new ViewdistrictAdapter(this, list_data);
        recyclerViewprovince.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener(ChooseActivity.this);

    }


    @Override
    public void onItemClick(int position) {
        province=list_data.get(position).getId();
        tvshowprovince.setText(list_data.get(position).getProvince());

    }


    private void getdistrict() {

        // create user body object
        District obj = new District();
        obj.setDistrictid(province);
        Map<String, String> param = new HashMap<>();
        param.put("id", province);


        Call<ResponseBody> request = RESTApiClient.getInstance().getApi().getdistrict(param);

        request.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String resStr = null;
                        try {
                            resStr = response.body().string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            JSONObject json = new JSONObject(resStr);

                            jsonresponse2 = json.toString();


                            displaydis();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        pbdistrict.setVisibility(View.GONE);

                    } else {

                        pbdistrict.setVisibility(View.GONE);
                        Toast.makeText(ChooseActivity.this, "No data is found", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                pbdistrict.setVisibility(View.GONE);
                Toast.makeText(ChooseActivity.this, t.getCause().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void displaydis() {
        try {
            JSONObject object = new JSONObject(jsonresponse2);
            JSONArray array = object.getJSONArray("district");
            for (int i = 0; i < array.length(); i++) {

                JSONObject jsonObject = array.getJSONObject(i);


                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");


                District model = new District();
                model.setDistrictid(id);
                model.setDistrictname(name);

                list_data2.add(model);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ViewprovinceAdapter adapter = new ViewprovinceAdapter(this, list_data2);
        recyclerViewdistrict.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener(new ViewprovinceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                district=list_data2.get(position).getDistrictid();
                tvshowdistrict.setText(list_data2.get(position).getDistrictname());

            btncontinuechoose.setVisibility(View.VISIBLE);
            }
        });

    }


}
