package ua.com.suntime;

import org.apache.http.message.BasicNameValuePair;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Context;
import android.view.Menu;

public class SightsListByRatingActivity extends ListActivity {
	
	private SuntimeSightsCollection sights;
	private final String TAG = "SightsListByRatingActivity";
	private Context context;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;
        
        new SuntimeSightsListByRatingWorker().execute(new BasicNameValuePair("order", "rating_desc"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_sights_list_by_rating, menu);
        return true;
    }
    
    public SuntimeSightsCollection getSights() {
		return sights;
	}

	public void setSights(SuntimeSightsCollection sights) {
		this.sights = sights;
	}

	private class SuntimeSightsListByRatingWorker extends SuntimeSightsWorker {
    	@Override
    	protected void onPostExecute(SuntimeSightsCollection response) {
    		setSights(response);
    		
    		if(response != null) {
    			SightsListByRatingAdapter adapter = new SightsListByRatingAdapter(context, response.getSights());
    			setListAdapter(adapter);
    			
    		}
    	}
    }
    
}
