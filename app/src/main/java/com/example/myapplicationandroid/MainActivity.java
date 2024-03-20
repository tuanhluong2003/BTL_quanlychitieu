package com.example.myapplicationandroid;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.myapplicationandroid.dao.AppDtb;
import com.example.myapplicationandroid.dao.LoaiThuDao;
import com.example.myapplicationandroid.dao.ThuDao;
import com.example.myapplicationandroid.dialog.ChiDialog;
import com.example.myapplicationandroid.dialog.LoaiChiDialog;
import com.example.myapplicationandroid.dialog.LoaiThuDialog;
import com.example.myapplicationandroid.dialog.ThuDialog;
import com.example.myapplicationandroid.entity.LoaiThu;
import com.example.myapplicationandroid.entity.Thu;
import com.example.myapplicationandroid.ui.chi.FragmentKhoanChi;
import com.example.myapplicationandroid.ui.chi.FragmentLoaiChi;
import com.example.myapplicationandroid.ui.thu.FragmentKhoanThu;
import com.example.myapplicationandroid.ui.thu.FragmentKhoanThuViewModel;
import com.example.myapplicationandroid.ui.thu.FragmentLoaiThu;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;


import com.example.myapplicationandroid.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    MainActivity mViewModel;
    private TabLayout tb;
    private ThuDao viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbar);
        final MainActivity currentContext = this;
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Fragment> fragments=getSupportFragmentManager().getFragments();
                Fragment fragment = fragments.get(fragments.size()-1);
                if(fragment instanceof FragmentLoaiThu){
                    LoaiThuDialog dialog = new LoaiThuDialog(currentContext,
                            (FragmentLoaiThu) fragment);
                    
                    dialog.show();
                } else if (fragment instanceof FragmentKhoanThu) {
                    ThuDialog dialog = new ThuDialog(currentContext,(FragmentKhoanThu) fragment );
                    dialog.show();
                    
                }
                List<Fragment> fragment1s=getSupportFragmentManager().getFragments();
                Fragment fragment1 = fragment1s.get(fragment1s.size()-1);
                if(fragment1 instanceof FragmentLoaiChi){
                    LoaiChiDialog dialog = new LoaiChiDialog(currentContext,
                            (FragmentLoaiChi) fragment1);
                    dialog.show();
                } else if (fragment instanceof FragmentKhoanChi) {
                    ChiDialog dialog = new ChiDialog(currentContext,(FragmentKhoanChi) fragment1 );
                    dialog.show();


                }
            }


        });
        DrawerLayout drawer = binding.drawerLayout;





        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)

                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                if(navDestination.getId() == R.id.nav_thoat){
                    finish();
                }
            }
        });
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        MenuInflater inflate= getMenuInflater();
        inflate.inflate(R.menu.main,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //AppDtb appDtb = AppDtb.getDatabase((Context) item);
//        ThuDao thuDao = AppDtb.INSTANCE.thuDao();

        if(item.getItemId() == R.id.xoadulieu){
            new DeleteAllData().execute();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public MainActivity getViewModel() {
        return mViewModel;
    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}