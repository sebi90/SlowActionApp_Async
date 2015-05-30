package sebi.slowactionapp_async;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    public EditText input;
    public TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = (EditText) this.findViewById(R.id.input);
        output = (TextView) this.findViewById(R.id.output);
    }

    public void clicked(final View v) {
        final String s = input.getText().toString();
        final String succFormatString = "SUCCESS";

        try {
            final long total = Long.parseLong(s);

            if (total < 0) {
                throw new NumberFormatException();
            }

            new MyAsyncTask() {

                @Override
                protected void onProgressUpdate(final Long... values) {
                    input.setText(String.valueOf(values[0]));
                }

                @Override
                protected void onPostExecute(final Long result) {
                    output.setText(String.format(succFormatString, result));
                }
            }.execute(Long.valueOf(total));

        } catch (final Exception e) {
            final String message = "FAILURE" + " " + e.getMessage();
            output.setText(message);
        }
    }

    public void changeInput(Long... values) {
        input.setText(String.valueOf(values[0]));
    }

    public void changeOutput(Long result) {
        output.setText(String.format("SUCCESS", result));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
