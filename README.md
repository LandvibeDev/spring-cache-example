# Spring Cache Example
Spring에서 제공하는 cache 라이브러리의 간단한 사용법을 담은 레포입니다.

## 클래스 설명
* CacheConfig
  * Cache를 사용하기 위한 설정을 담은 객체
* UserRepository
  * 사용자 정보를 메모리에 담아두는 객체
* UserService
  * UserRepository에 담아둔 사용자 정보를 캐싱하는 객체
  * `public Optional<User> getUser(int uid)`
    * 같은 uid가 인자로 전달되면 같은 사용자 정보를 반환한다.
  * `public void saveUser(User user)`
    * 새로운 사용자가 저장되면 캐시를 삭제한다.
* UserServiceCacheTest
  * UserService의 캐시가 잘 동작 하는지 테스트 하기 위한 테스트 케이스
  * 실제 캐시가 저장되는 CacheManager에서 값을 확인하여 테스

## 테스트 케이스 실행시키는 법
```bash
./gradlew test --tests "com.landvibe.cacheexample.service.UserServiceCacheTest"
```
