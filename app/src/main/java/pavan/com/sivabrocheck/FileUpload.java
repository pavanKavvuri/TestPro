package pavan.com.sivabrocheck;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



class FileUpload extends AsyncTask<String,Void,String>{

  //  private ProgressDialog dialog;

    private Context conn;
    int serverResponseCode;
    String serverResponseMessage="";
    String result,response;

    String path="/storage/sdcard/flower1.jpg";
    String server="http://www.thirdi.16mb.com/signup.php";
    public int fileSize=0,fileOffSet=0,bufferlen=1000;

    //FileInputStream fileInputStream=null;
    String boundary="*****",linend="\r\n",twohypens="--";

    byte[] buffer=new byte[bufferlen];

    @Override
    protected void onPreExecute(){
 }

    @Override
    protected String doInBackground(String... args)
    {

        try{


            FileInputStream fileInputStream = new FileInputStream(new File(path) );
                /*
                * Creating instance of HttpUrlConnection and setting properties
                *  of connection required for file Upload
                */
            URL url=new URL(server);
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setChunkedStreamingMode(1000);
            httpURLConnection.setRequestProperty("Connection-Type", "Keep-Alive");
            // contentType="multipart/form-data;boundary=" + boundary;
            httpURLConnection.setRequestProperty("Content-Type","multipart/form-data;boundary=" + boundary);
                /*
                * Creating multipart form-data header in order
                * to send data to server as file
                * */
            OutputStream outputStream=httpURLConnection.getOutputStream();
            outputStream.write((twohypens+boundary+twohypens+linend).getBytes());
            String fileMetaContent="Content-Disposition:form-data;name='file';filename='"+path+"'"+linend;
            outputStream.write(fileMetaContent.getBytes());
            outputStream.write(linend.getBytes());
                /*
                * Now we are gonna send filestream contents as chuncks
                * to output stream so that to avoid outofmemory exception
                * */
            fileSize=fileInputStream.available();
            while(fileOffSet<fileSize)
            {
                bufferlen=fileInputStream.read(buffer,0,bufferlen);
                outputStream.write(buffer,0,bufferlen);
                fileOffSet+=bufferlen;
            }

            outputStream.write(linend.getBytes());
            outputStream.write((twohypens+boundary+twohypens+linend).getBytes());

            serverResponseCode =  httpURLConnection.getResponseCode();
            serverResponseMessage =  httpURLConnection.getResponseMessage();

            if(serverResponseCode==200) {
                result = "Success";

            }

            else result=null;




            outputStream.flush();
            outputStream.close();
            fileInputStream.close();



        }
        catch (MalformedURLException mfue)
        { System.out.println(mfue.getLocalizedMessage()); }
        catch (IOException ioe)
        { System.out.println(ioe.getMessage()); }

        return result;

    }

//88999966g

    @Override
    protected void onPostExecute(String result)
              {

                if(result.toString()=="Success")    Toast.makeText(conn," Hey! File upload Completed Successfully ", Toast.LENGTH_LONG).show();

               //   if (dialog.isShowing()) {
                //      dialog.dismiss();
                 // }

            //    else if(result.toString()=="null")
//        Toast.makeText(conn," Hey! File upload Completed Successfully ", Toast.LENGTH_LONG).show();

       //System.out.println(result);
    }

}