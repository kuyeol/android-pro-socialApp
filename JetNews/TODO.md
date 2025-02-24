
```
// UI와 데이터 간의 로직을 처리하는 부분을 추가 설명과 함께 코드로 제공합니다.
// 이 코드는 이전 답변에서 작성한 코드들을 기반으로 하며,
// ViewModel, LiveData, Data Binding, StateFlow 등을 사용하여
// UI와 데이터를 효율적으로 연결하고 관리하는 방법을 보여줍니다.
//
// Google의 최신 Android 개발 권장 사항 (Jetpack, MVVM 패턴, Coroutines)을 따릅니다.
```

---

```
// ----------------------------------------------------------------------------------
// 1. Model (Data Class)
// ----------------------------------------------------------------------------------
```

```kotlin
package com.example.yourproject.data.model

// 백엔드에서 반환하는 Post 데이터 구조와 일치해야 합니다.
data class Post(
    val id: String,
    val title: String,
    val content: String,
    val author: String? = null, // 추가 필드 예시
    val imageUrl: String? = null // 추가 필드 예시
)
```

---

---
```
// ----------------------------------------------------------------------------------
// 2. API Interface (Retrofit)
// ----------------------------------------------------------------------------------
```

```kotlin
package com.example.yourproject.data.network

import com.example.yourproject.data.model.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PostService {
    @GET("/posts/{postId}") // 백엔드 API 엔드포인트 (예시)
    suspend fun getPost(@Path("postId") postId: String): Response<Post>
}
```
---

---
```
// ----------------------------------------------------------------------------------
// 3. Retrofit Client
// ----------------------------------------------------------------------------------

```

```kotlin
package com.example.yourproject.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://your-backend-api-url.com/" // 실제 백엔드 API URL로 변경

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // BODY 레벨 로깅 (요청/응답 본문 포함)
        })
        .build()

    val instance: PostService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(PostService::class.java)
    }
}
```
---


---
```
// ----------------------------------------------------------------------------------
// 4. Result (Sealed Class)
// ----------------------------------------------------------------------------------
```
```kotlin
package com.example.yourproject.data

// API 호출 결과를 나타내는 Sealed Class (성공/실패)
sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>() // 로딩 상태 추가 (선택 사항)
}
```
---

---
```
// ----------------------------------------------------------------------------------
// 5. Repository
// ----------------------------------------------------------------------------------
```
```kotlin
package com.example.yourproject.data

import com.example.yourproject.data.model.Post
import com.example.yourproject.data.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class PostRepository {
    private val postService = RetrofitClient.instance

    // suspend fun getPost(postId: String?): Result<Post> { ... } // 이전 버전 (Result 반환)

    // LiveData 대신 Flow를 사용하는 새로운 getPost 함수 (더 최신 방식)
    suspend fun getPost(postId: String?): Post? {
        return withContext(Dispatchers.IO) {
            try {
                if (postId == null) {
                    // Result.Error 대신 null 반환
                    return@withContext null
                }
                val response = postService.getPost(postId)
                if (response.isSuccessful) {
                    response.body() // Post 객체 반환 (null일 수 있음)
                } else {
                    // Result.Error 대신 null 반환
                    null
                }
            } catch (e: Exception) {
                // Result.Error 대신 null 반환
                null
            }
        }
    }
}
```
---


---
```
// ----------------------------------------------------------------------------------
// 6. ViewModel
// ----------------------------------------------------------------------------------
```
```kotlin
package com.example.yourproject.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yourproject.data.PostRepository
import com.example.yourproject.data.model.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// UI State를 위한 data class
data class PostUiState(
    val isLoading: Boolean = false,
    val post: Post? = null,
    val errorMessage: String? = null
)
class PostViewModel : ViewModel() {

    private val repository = PostRepository()

    // LiveData 사용 (이전 방식)
    /*
    private val _post = MutableLiveData<Post?>()
    val post: LiveData<Post?> = _post

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage
    */

    // StateFlow 사용 (최신 방식, 권장)
    private val _uiState = MutableStateFlow(PostUiState())
    val uiState: StateFlow<PostUiState> = _uiState.asStateFlow()


    fun loadPost(postId: String) {
        viewModelScope.launch {
            //_isLoading.value = true // LiveData 사용 시
            _uiState.value = _uiState.value.copy(isLoading = true) // StateFlow 사용 시
            val post = repository.getPost(postId)
            //_isLoading.value = false // LiveData 사용 시
            _uiState.value = _uiState.value.copy(isLoading = false) // StateFlow 사용 시

            if (post != null) {
                //_post.value = post // LiveData 사용 시
                _uiState.value = _uiState.value.copy(post = post) // StateFlow 사용 시
            } else {
                //_errorMessage.value = "Failed to load post" // LiveData 사용 시
                _uiState.value = _uiState.value.copy(errorMessage = "Failed to load post")  // StateFlow 사용 시
            }
        }
    }
}
```

---


---

```
// ----------------------------------------------------------------------------------
// 7. Activity / Fragment (UI)
// ----------------------------------------------------------------------------------
```
```kotlin
package com.example.yourproject.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels // 또는 androidx.fragment.app.viewModels (Fragment에서)
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.yourproject.databinding.ActivityPostDetailBinding // View Binding 사용 (Data Binding도 가능)
import com.example.yourproject.data.model.Post
import kotlinx.coroutines.launch

class PostDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostDetailBinding
    private val viewModel: PostViewModel by viewModels() // ViewModel 인스턴스 생성

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val postId = intent.getStringExtra("POST_ID") // Intent에서 postId 가져오기 (예시)

        observeUiState() // StateFlow 관찰 (또는 LiveData 관찰)
        if (postId != null) {
            viewModel.loadPost(postId)
        } else{
            Toast.makeText(this, "Post ID is required", Toast.LENGTH_SHORT).show()
            finish() // 액티비티 종료
        }
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) { // STARTED 상태에서만 수집
                viewModel.uiState.collect { uiState ->
                    // UI 업데이트
                    binding.progressBar.visibility = if (uiState.isLoading) View.VISIBLE else View.GONE

                    if (uiState.errorMessage != null) {
                        Toast.makeText(this@PostDetailActivity, uiState.errorMessage, Toast.LENGTH_SHORT).show()
                    }

                    uiState.post?.let { post ->
                        // 데이터 바인딩을 사용하는 경우, binding.post = post
                        // 또는 수동으로 뷰에 데이터 설정
                        binding.titleTextView.text = post.title
                        binding.contentTextView.text = post.content
                        // ... 기타 뷰 업데이트 ...
                    }
                }
            }
        }
    }

    /* LiveData 사용 시 (이전 방식)
    private fun observeViewModel() {
        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.errorMessage.observe(this) { errorMessage ->
            if(errorMessage != null) {
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.post.observe(this) { post ->
            if (post != null) {
                // 데이터 바인딩을 사용하는 경우, binding.post = post

                // 또는 수동으로 뷰에 데이터 설정
                binding.titleTextView.text = post.title
                binding.contentTextView.text = post.content
                // ... 기타 뷰 업데이트 ...
            }
        }
    }
    */
}
```

---
