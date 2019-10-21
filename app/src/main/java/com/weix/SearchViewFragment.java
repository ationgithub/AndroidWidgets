package weix.xjp.searchtips;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.weix.R;

@SuppressLint("ValidFragment")
public class SearchViewFragment extends Fragment {

    SearchView searchView;
    TextView cancelBtn;
    ImageView searchIcon;
    Context mContext;


    SearchViewFragment(Context context) {
        mContext=context;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_searchview, container, false);

        searchView = (SearchView) view.findViewById(R.id.msearch);
        int closeImagId = getResources().getIdentifier("android:id/search_close_btn", null, null);
        ImageView closeImag = (ImageView) searchView.findViewById(closeImagId);
        closeImag.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
        cancelBtn = (TextView) view.findViewById(R.id.cancel);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.cancel:
                        Toast.makeText(mContext, "You have clicked the Cancel Button!", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(mContext, "You have clicked the Submit Button!", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}
