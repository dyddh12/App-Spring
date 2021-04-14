package com.human.edu;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import core.AsyncResponse;
import core.JsonConverter;
import core.PostResponseAsyncTask;

/**
 * 이 액티비티에서는 리사이클러뷰에 RestAPI Json 데이터를 바인딩 시키는 기능
 * List객체(Json데이터바인딩)-어댑터클래스(데이터와 뷰객체중간)-리사이클러뷰
 */
public class SubActivity extends AppCompatActivity {
    //리사이클러 뷰를 사용할 멤버변수(필드변수) 생성
    private RecyclerAdapter mRecyclerAdapter;
    private List mItemList = new ArrayList<MemberVO>();
    //어댑터에서 선택한 값 확인 변수(선택한 회원을 삭제하기 위해서)
    private int currentCursorId = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        //객체 생성
        mRecyclerAdapter = new RecyclerAdapter(mItemList);
        //리사이클러뷰xml과 어댑터클래스를 바인딩
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mRecyclerAdapter);//데이터없는 빈 어댑터를 뷰화면에 바인딩시킴
        getAllData();
    }

    //RestAPI 서버에서 전송받은 데이터를 리사이클러뷰 어댑터에 바인딩 시킴
    private void getAllData() {
        //RestAPI 서버와 비동기 통신 시작
        String requestUrl = "http://192.168.100.18:8080/android/list";
        HashMap postDataParams = new HashMap();
        postDataParams.put("mobile","android");
        List resultList = new ArrayList<>();//RestAPI에서 보내온 jSon데이터가 저장공간 생성
        PostResponseAsyncTask readTask = new PostResponseAsyncTask(SubActivity.this, postDataParams, new AsyncResponse() {

            @Override
            public void processFinish(String output) {
                Log.i("RestAPI 텍스트: ", output);
                ArrayList<MemberVO> memberList = new JsonConverter<MemberVO>().toArrayList(output, MemberVO.class);
                //위 컨버트한 memberList변수를 어댑터에 바인딩 시키기(아래)
                for(MemberVO vaule: memberList) {
                    //resultList 에 1개 레코드씩 저장 -> 어댑터에 데이터 바인딩예정

                }
            }
        });
    }
}