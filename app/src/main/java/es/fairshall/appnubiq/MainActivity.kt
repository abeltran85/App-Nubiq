package es.fairshall.appnubiq

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import es.fairshall.appnubiq.Common.Common
import es.fairshall.appnubiq.Model.APIResponse
import es.fairshall.appnubiq.Remote.IMyApi
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    internal lateinit var mService:IMyApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Inicia Servicio
        mService = Common.api

        btnSubmit.setOnClickListener{
            authenticateUser(inputuser.text.toString(),password.text.toString(),Integer.parseInt(idClient.text.toString()))
        }
    }

    private fun authenticateUser(inputuser: String, password: String, idClient: Int) {
        mService.LoginUser(idClient, inputuser, password)
            .enqueue(object : Callback<APIResponse> {
                override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                    if (response.body()!!.error)
                        Toast.makeText(this@MainActivity, response.body()!!.error_msg, Toast.LENGTH_SHORT).show()
                    else
                        Toast.makeText(this@MainActivity, "Login Success!!", Toast.LENGTH_SHORT).show()
                    finish()
                }
            })
    }
}

