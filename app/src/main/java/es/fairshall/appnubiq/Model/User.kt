package es.fairshall.appnubiq.Model

class User {
     var id_client:Int=0
     var nombre:String=""
     var password:String=""

     constructor(){}

     constructor(id_Client: Int, nombre:String, password: String){
          this.id_client = id_Client
          this.nombre = nombre
          this.password = password

     }
}