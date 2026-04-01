package com.wbjang.data_persistence_codelab_flight_search.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflineFlightSearchRepository @Inject constructor(private val airportDao: AirportDao, private val favoriteDao: FavoriteDao) :FlightSearchRepository {
    override fun getAllAirportsStream(): Flow<List<Airport>> = airportDao.getAllItems()
    override fun searchAirportsStream(query: String): Flow<List<Airport>> = airportDao.searchAirports(query)
    override fun getAirportByCodeStream(iataCode: String): Flow<Airport> = airportDao.getAirportByCode(iataCode)
    override fun getArrivalAirportsStream(departureCode: String): Flow<List<Airport>> = airportDao.getArrivalAirports(departureCode)
    override fun getFavoriteAirportsStream(): Flow<List<Favorite>> = favoriteDao.getAllItems()
    override suspend fun insertFavorite(favorite: Favorite) = favoriteDao.insertFavorite(favorite)
    override suspend fun deleteFavorite(departureCode: String, destinationCode: String) = favoriteDao.deleteFavoriteByCodes(departureCode, destinationCode)
}