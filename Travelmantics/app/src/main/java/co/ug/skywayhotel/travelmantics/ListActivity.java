package co.ug.skywayhotel.travelmantics;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }
//
//    }
//}
@Override
public boolean onOptionsItemSelected(MenuItem item){
    switch(item.getItemId()){
        case R.id.insertMenu:
            Intent intent = new Intent(this, DealActivity.class);
            startActivity(intent);
            return true;
//        case R.id.logoutMenu:
//            AuthUI.getInstance()
//                    .signOut(this)
//                    .addOnCompleteListener(new OnCompleteListener<Void>() {
//                        public void onComplete(@NonNull Task<Void> task) {
//                            Log.d("Logout", "onComplete: User logged out");
//                            FirebaseUtil.attachListener();
//                        }
////                    });
//            FirebaseUtil.detachListener();
//            return true;
        default:
            return super.onOptionsItemSelected(item);
    }
}

    @Override
    protected void onPause() {
        super.onPause();
        FirebaseUtil.detachListener();
    }

    @Override
    protected void onResume() {
        super.onResume();

        FirebaseUtil.openFbReference("traveldeals", this);
        RecyclerView rvDeals = findViewById(R.id.rvDeals);
        final DealAdapter adapter = new DealAdapter();
        rvDeals.setAdapter(adapter);
        LinearLayoutManager dealsLayoutManager =
                new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        rvDeals.setLayoutManager(dealsLayoutManager);

        FirebaseUtil.attachListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_activity_menu, menu);
        MenuItem insertMenu = menu.findItem(R.id.insertMenu);
        if (FirebaseUtil.isAdmin){
            insertMenu.setVisible(true);
        }
        else{
            insertMenu.setVisible(false);
        }

        return true;
    }

    public void showMenu(){
        invalidateOptionsMenu();
    }
}