package sebi.slowactionapp_async;

import android.os.AsyncTask;

/**
 * Created by Sebi on 30.05.15.
 */
public class MyAsyncTask extends AsyncTask<Long, Long, Long> {

    @Override
    protected Long doInBackground(final Long... params) {

        long rest = params[0].longValue();

        while (rest > 0) {
            final long thisTime = Math.min(rest, 1000L);
            try {
                Thread.sleep(thisTime);
            } catch (final Exception e) {
            }

            rest -= thisTime;
            this.publishProgress(Long.valueOf(rest));
        }

        return Long.valueOf(params[0]);
    }
}
