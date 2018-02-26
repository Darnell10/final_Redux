package c4q.com.final_redux;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by D on 2/25/18.
 */

public interface Api {

    //https://dog.ceo/api/breed/terrier/images/random

    @GET("{breed}/images/random")
    Call<BreedModel> getDog(@Path("breed")String breed);
}
