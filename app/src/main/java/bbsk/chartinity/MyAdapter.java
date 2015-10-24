package bbsk.chartinity;

/**
 * Created by Boyan on 10/24/2015.
 */
public class MyAdapter extends android.support.v4.app.FragmentPagerAdapter {

    public MyAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int arg0) {
        android.support.v4.app.Fragment fragment=null;
        if(arg0==0)
        {
            fragment = new Camera();
        }
        else if(arg0 == 1)
        {
            fragment = new History();
        }
        else if(arg0 == 2)
        {
            fragment = new Profile();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
