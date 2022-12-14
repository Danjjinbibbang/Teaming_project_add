package com.example.teaming_project_add;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    GridView gridView;
    EditText editText;
    Button button;
    ProjectAdapter projectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gridView = (GridView)findViewById(R.id.gridView);
        editText = (EditText)findViewById(R.id.editText);
        button = (Button)findViewById(R.id.button);

        projectAdapter = new ProjectAdapter();
        projectAdapter.addItem(new ProjectItem("팀프로젝트",R.drawable.ic_users));

        gridView.setAdapter(projectAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"팀프로젝트 : "+ projectAdapter.getItem(i).getText().toString(), Toast.LENGTH_LONG).show();

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();
                projectAdapter.addItem(new ProjectItem(text,R.drawable.ic_users));
                //아이템추가
                projectAdapter.notifyDataSetChanged();
            }
        });

    }

    //추가된 소스, ToolBar에 menu.xml을인플레이트함
    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.bell_menu, menu);
        return true;
    }

    //추가된 소스, ToolBar에 추가된 항목의 select 이벤트를 처리하는 함수
    @Override
    public  boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.navigation_notifications:
                Toast.makeText(getApplicationContext(), "알림확인 창입니다.", Toast.LENGTH_LONG).show();
                return true;
        }
        return false;
    }

    class ProjectAdapter extends BaseAdapter{
        ArrayList<ProjectItem> items = new ArrayList<ProjectItem>();
        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(ProjectItem singerItem){
            items.add(singerItem);
        }

        @Override
        public ProjectItem getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ProjectViewer singerViewer = new ProjectViewer(getApplicationContext());
            singerViewer.setItem(items.get(i));
            return singerViewer;
        }
    }
}
