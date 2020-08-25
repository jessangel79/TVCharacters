package io.angelappdev.tvcharacters

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class TVCharactersApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        // for tests dev : deleteRealmIfMigrationNeeded - To delete when deploy the app o the store
//        val config = RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().inMemory().build()
//        Realm.setDefaultConfiguration(config)
    }
}
