package com.randhypi.mymovie.ui.home.movies

import androidx.lifecycle.ViewModel
import com.randhypi.mymovie.data.movies.DummyMovies
import com.randhypi.mymovie.data.movies.Movies

class MoviesViewModel() : ViewModel() {
    companion object {
        val TAG = MoviesViewModel::class.java.simpleName

    }

    val getMovies: ArrayList<Movies> = DummyMovies.moviesDummy() as ArrayList<Movies>

//    val _listMovies  = MutableLiveData<ArrayList<Movies>>()
//
//    val retrofit: Retrofit = Retrofit.Builder()
//        .baseUrl("https://api.themoviedb.org/3/movie/")
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()

//    fun setListMovies(){
//        val listMovies = ArrayList<Movies>()
//
//        var service: ApiServices = retrofit.create(ApiServices::class.java)
//        val callMovies: Call<ResponseMovies> = service.getMovies()
//        callMovies.enqueue(object : Callback<ResponseMovies> {
//            override fun onResponse(
//                call: Call<ResponseMovies>,
//                response: Response<ResponseMovies>
//            ) {
//                try {
//                    if (!response.isSuccessful) {
//                        Log.d(TAG, response.code().toString())
//                        return
//                    }
//                    val responseBody = response.body()
//                    val movies = responseBody?.results
//
//                    for (i in 0 until movies?.size!!){
//                        var id = movies.get(i)?.id.toString()
//                        var original_language = movies.get(i)?.originalLanguage
//                        var original_title = movies.get(i)?.originalTitle
//                        var title = movies.get(i)?.title
//                        var release_date = movies.get(i)?.releaseDate
//                        var poster = "https://image.tmdb.org/t/p/original${movies.get(i)?.posterPath}"
//                        var overview = movies.get(i)?.overview
//                        var popularity = movies.get(i)?.popularity
//
//                        var moviesList = Movies(id,original_language,original_title,title,release_date,poster,overview,popularity)
//                        listMovies.add(moviesList)
//                    }
//
//                    _listMovies.postValue(listMovies)
//                } catch (e: Exception) {
//                    Log.d(TAG, e.toString())
//                }
//            }
//            override fun onFailure(call: Call<ResponseMovies>, t: Throwable) {
//                Log.d(TAG, t.toString())
//            }
//        })
//
//    }
//
//    fun getMovies(): LiveData<ArrayList<Movies>>{
//        return _listMovies
//    }


}