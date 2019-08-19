package com.example.android.projet

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.projet.entities.Commande
import com.example.android.projet.entities.Pharmacie
import com.example.android.projet.entities.Utilisateur
import com.example.android.projet.entities.Ville
import com.example.android.projet.local_storage.*

//import androidx.test.InstrumentationRegistry
//import androidx.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Assert

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.sql.Date
import java.sql.Timestamp

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    var mDataBase: AppDatabase?=null
    var dateConverter : DateConverter?=null
    var timestampConverter : TimestampConverter?=null
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().context

        //val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.example.android.projet", appContext.packageName)
    }

    @Before
    fun initDB() {
        mDataBase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry. getInstrumentation().context,AppDatabase::class.java).build()
    }

    @Test fun testInsertAndGetPharmacie() {
        val v = Ville("ain hammadi")
        mDataBase?.getVilleDAO()?.addVille(v)
        val v1 = Pharmacie(
            "Test", "13éé",
            "dfsd", "dfsdf",  1,"dfsd"
        );
        mDataBase?.getPharmacieDAO()?.addPharmacie(v1)
        val v2 = mDataBase?.getPharmacieDAO()?.getPharmacieByNom("Test")?.get(0)
        Assert.assertEquals(v2, v1)
    }
    @Test fun testInsertAndGetCommande() {
        val v = Ville("ain hammadi")
        mDataBase?.getVilleDAO()?.addVille(v)
        val p1 = Pharmacie(
            "Test", "13éé",
            "dfsd", "dfsdf",  1,"dfsd"
        );
        val u1 = Utilisateur(
            "Test", "13éé",
            "dfsd", 1234,
            "dfsd", 1
        );
        val c1= Commande("teste", Date(123), "1233", "fait", 1, 1)
        mDataBase?.getPharmacieDAO()?.addPharmacie(p1)
        mDataBase?.getUtilisateurDAO()?.addUtilisateur(u1)
        mDataBase?.getCommandeDAO()?.addCommande(c1)
        val c2 = mDataBase?.getCommandeDAO()?.getCommande(1)?.get(0)
        Assert.assertEquals(c2, c1)
    }

    @Test fun testInsertUser() {
        val v1 = Utilisateur(
            "Test", "13éé",
            "dfsd", 1234, "dfsd", 1
        );
        mDataBase?.getUtilisateurDAO()?.addUtilisateur(v1)
        val v2 = mDataBase?.getUtilisateurDAO()?.getUtilisateur(1)?.get(0)
        Assert.assertEquals(v2, v1)
    }
    @After
    fun closeDb(){
        mDataBase?.close()
    }
}
