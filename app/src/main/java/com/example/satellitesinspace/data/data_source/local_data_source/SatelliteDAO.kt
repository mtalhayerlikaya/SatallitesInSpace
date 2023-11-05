package com.example.satellitesinspace.data.data_source.local_data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.satellitesinspace.data.model.SatelliteListItem

@Dao
interface SatelliteDAO {
    /*@Query("SELECT * FROM crypto")
    suspend fun getAllCryptosFromDB(): List<CryptoEntity>
    @
    @Query("SELECT * FROM crypto WHERE cryptoID = :id")
    suspend fun getCryptoByID(cryptoID: String): CryptoEntity*/

    @Insert
    suspend fun insertSatelliteDetail(satelliteDetail: SatelliteDetailEntity)

    @Query("SELECT * FROM satellite_detail WHERE id = :satelliteId")
    suspend fun getSatelliteDetailFromDB(satelliteId: Int): SatelliteDetailEntity

    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCryptos(cryptos: List<CryptoEntity>)*/

/*    @Query("SELECT * FROM crypto")
    suspend fun getAllSatellites(): List<SatelliteListItem>

    @Query("SELECT * FROM crypto WHERE name LIKE '%' || :searchPattern || '%' OR symbol LIKE '%' || :searchPattern || '%'")
    suspend fun findCryptoByName(searchPattern: String): List<CryptoEntity>*/

}