package es.fairshall.appnubiq.Common

import es.fairshall.appnubiq.Remote.IMyApi
import es.fairshall.appnubiq.Remote.RetrofitClient

object Common{
    val BASE_URL="http://loquesea/api"

    val api: IMyApi
        get() = RetrofitClient.getClient(BASE_URL).create(IMyApi::class.java)
}