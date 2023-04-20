# Idus
아이디어스 과제 샘플

개발 언어 : Kotlin

Clean Architecture : Data, Domain, Presentation Layer 구조

* DataLayer는 Repository Pattern 사용
* Domain Layer는 UseCase를 이용한 비즈니스 로직 구현
* Presentation Layer는 ViewModel 및 UIState를 통한 구현

디자인 패턴 : MVVM

DI : Dagger(Hilt)

네트워크 통신 라이브러리 : Retorift2, Okhttp3, Gson

이미지 처리 라이브러리 : Glide

그 외 viewBinding, dataBinding을 통한 UI 구현 및 DiffCallbackUtil 사용으로 리스트 변경사항 적용
