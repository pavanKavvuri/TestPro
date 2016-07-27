package pavan.com.sivabrocheck;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

//dfghjksdfgh

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button upload= (Button) findViewById(R.id.but);

        assert upload != null;
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new FileUpload().execute("wertyu");
//
//git
            }});

    }


}
