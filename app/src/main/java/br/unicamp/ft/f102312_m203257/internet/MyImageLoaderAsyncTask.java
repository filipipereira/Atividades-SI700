package br.unicamp.ft.f102312_m203257.internet;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;


public class MyImageLoaderAsyncTask extends AsyncTask<String, Bitmap, Boolean> {

    ImageView imageView;
    Boolean  keepRunning;


    public MyImageLoaderAsyncTask(ImageView imageView){
        this.imageView = imageView;
        keepRunning = true;
    }

    public void stopRunning(){
        keepRunning = false;
    }

    @Override
    protected void onPreExecute(){
    }

    @Override
    protected Boolean doInBackground(String... args){
        int count = 0;
        while (keepRunning){
            try {
            Bitmap bmp = ImageLoader.baixarImagem(args[count % args.length]);
            publishProgress(bmp);
            count++;

                Thread.sleep(4000);
            }
            catch (InterruptedException e){
                System.out.println("InterruptedException");
            }
            catch (IOException e){
                System.out.println("IOException");
            }
        }
        return true;
    }

    @Override
    protected void onProgressUpdate(Bitmap... args){

        if (args[0] != null) {
            imageView.setImageBitmap(args[0]);
        }
    }

    @Override
    protected void onPostExecute(Boolean args){

    }
}
