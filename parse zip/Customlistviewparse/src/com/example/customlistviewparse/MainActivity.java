package com.example.customlistviewparse;
 
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
 
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
 
public class MainActivity extends Activity {
// Declare Variables
ListView listview;
List<ParseObject> ob;
ProgressDialog mProgressDialog;
ListViewAdapter adapter;
private List<WorldPopulation> worldpopulationlist = null;
 
@Override
public void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.listview_main);

// Execute RemoteDataTask AsyncTask
new RemoteDataTask().execute();
}
 
// RemoteDataTask AsyncTask
private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
@Override
protected void onPreExecute() {
super.onPreExecute();
// Create a progressdialog
mProgressDialog = new ProgressDialog(MainActivity.this);
// Set progressdialog title
mProgressDialog.setTitle("Parse.com Custom ListView Tutorial");
// Set progressdialog message
mProgressDialog.setMessage("Loading...");
mProgressDialog.setIndeterminate(false);
// Show progressdialog
mProgressDialog.show();
}
 
@Override
protected Void doInBackground(Void... params) {
// Create the array
worldpopulationlist = new ArrayList<WorldPopulation>();
try {
// Locate the class table named "Country" in Parse.com
ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
"Country");
// Locate the column named "ranknum" in Parse.com and order list
// by ascending
query.orderByAscending("ranknum");
ob = query.find();
for (ParseObject country : ob) {
WorldPopulation map = new WorldPopulation();
map.setRank((String) country.get("rank"));
map.setCountry((String) country.get("country"));
map.setPopulation((String) country.get("population"));
worldpopulationlist.add(map);
}
} catch (ParseException e) {
Log.e("Error", e.getMessage());
e.printStackTrace();
}
return null;
}
 
@Override
protected void onPostExecute(Void result) {
// Locate the listview in listview_main.xml
	
	listview = (ListView)findViewById(R.id.listView1);
	
//listview = (ListView) findViewById(R.id.listview);




// Pass the results into ListViewAdapter.java
adapter = new ListViewAdapter(MainActivity.this,
worldpopulationlist);
// Binds the Adapter to the ListView
listview.setAdapter(adapter);
// Close the progressdialog
mProgressDialog.dismiss();
}
}
}