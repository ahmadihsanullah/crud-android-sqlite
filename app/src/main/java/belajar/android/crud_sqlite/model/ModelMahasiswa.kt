package belajar.android.crud_sqlite.model



class ModelMahasiswa {

     var id: String? = ""
               get() = field
               set(value) {
                   field = value
               }
     var nama: String = ""
               get() = field
               set(value) {
                   field = value
               }
     var kelas: String = ""
               get() = field
               set(value) {
                   field = value
               }

    constructor(id: String?, nama: String, kelas: String){
        this.id = id
        this.nama = nama
        this.kelas = kelas
    }

    constructor(){

    }


}