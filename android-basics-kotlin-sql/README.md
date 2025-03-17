# SQL Basics

# SQLite Database File Path:

> /data/data/com.PACKAGE_NAME/databases/FILE_NAME.db
>


This folder contains the source code for the SQL Basics codelab.

# Introduction

The SQLBasics project is a single screen app that simply instantiates a Room database. Rather than interacting with the database through Kotlin code, you'll learn the fundamentals of SQL, including writing queries to get data, as well as how to insert and delete from a database.

When the app is running, you'll be able to send SQL commands to the database via Android Studio's Database Inspector.

# Pre-requisites
* Experience navigating an Android Studio Project

# Getting Started
1. Install Android Studio, if you don't already have it.
2. Download the sample.
3. Import the sample into Android Studio.
4. Build and run the sample.


Intuition
실행 속도
- new 를 사용해 객체 생성이 많아 질수록 느림
- 참조를 사용하는것이 빠름
- 배열의 크기도 영향을 미침 [0]과 [1] 속도 차이 있음
-
메모리 사용

실행 시 마다 결과가 동일하진 않음

구현 코드 설명
배열로 구성하여 2차원 배열에 결과를 누적하여 처음 결과를 반환 하도록함

결과가 동일한 경우 처음 결과를 반환하도록
속도는 단일 배열 생성으로 결과 반환하는 경우와 유의미한 차이없음
for 루프 사용 시 두번째 표현식에서 상위 루프를 참조해 따라가도록
Approach
Complexity
Time complexity:
배열로 구현 시
- Runtime : 38ms
- Beats : 44.08%

리스트로 구현 시
- Runtime : 39ms
- Beats : 40%

Space complexity:
배열로 구현 시
- Memory : 44.67MB
- Beats : 98.36%

리스트로 구현 시
- Memory : 44.8MB
- Beats : 74.42%

실행속도 최적화 솔루션 코드
class Solution {
public int[] twoSum(int[] nums, int target) {
int n=nums.length;
int ar[]=new int[2];
int size=0, flag=0;
HashMap<Integer,Integer> hm = new HashMap<>();
for(int i=0;i<n;i++)
{
if(hm.containsKey(target - nums[i]))
{
ar[0]=hm.get(target - nums[i]);
ar[1]=i;
break;
}
hm.put(nums[i],i);
}
return ar;

    }
}
# Complexity

Runtime `2ms`
Beats `98.84%`

Memory `45.35MB`
Beats `9.90%`
구현 Code
class Solution {
public int[] twoSum(int[] nums, int target) {

        int i = 0;
        int j = 0;
        int find = 0;
        int size = nums.length;

         int[][] newArray = new int[1][];  
         int arri = 0;

        for (i = 0; i < size; i++) {
            for (j = i + 1; j < size; j++) {
                find = nums[i] + nums[j];
                if (find == target) {
                     newArray[arri] = new int[] { i , j };
                     arri++;
                return   newArray[0];
                }

            }

        }
        return  newArray[0];
    }
}




---3. TODO 항목 구현 가이드 (계속)
(1) APK 자동 빌드 구성 (계속)
Gradle 설정:

Jenkins에 Gradle 설치:
Manage Jenkins → Global Tool Configuration → Gradle 섹션에서 버전 선택 (예: 8.5).
Install automatically 체크 후 저장.
빌드 스텝에서 Gradle 사용:
Add build step → Invoke Gradle script 선택.
Tasks에 clean assembleRelease 입력 (APK 빌드용 명령어).
APK 서명:

Keystore 파일 업로드:
Manage Jenkins → Credentials → System → Global credentials → Add Credentials
Type: Secret file 선택 → keystore 파일 업로드.
build.gradle에 서명 설정 추가:
android {  
signingConfigs {  
release {  
storeFile file(System.getenv("KEYSTORE_PATH"))  
storePassword System.getenv("KEYSTORE_PASSWORD")  
keyAlias System.getenv("KEY_ALIAS")  
keyPassword System.getenv("KEY_PASSWORD")  
}  
}  
buildTypes {  
release {  
signingConfig signingConfigs.release  
}  
}  
}

빌드 스텝에서 환경 변수 주입:
Execute shell 추가 후 아래 명령어 입력:
export KEYSTORE_PATH=${WORKSPACE}/path/to/keystore.jks  
export KEYSTORE_PASSWORD=your_password  
export KEY_ALIAS=your_alias  
export KEY_PASSWORD=your_password

APK 아티팩트 저장:

Post-build Actions → Archive the artifacts 선택.
Files to archive에 **/*.apk 입력 (빌드된 APK 자동 저장).
(2) 깃 커밋/푸시마다 빌드
GitHub 웹훅 설정:

플러그인 설치:
Manage Jenkins → Plugins → Available plugins에서 GitHub Integration 설치.
Jenkins Job 설정:
소스 코드 관리 → Git → Repositories에 GitHub URL 입력.
Branches to build에 */main (기본 브랜치 지정).
Build Triggers → GitHub hook trigger for GITScm polling 체크.
GitHub 레포지토리 설정:
GitHub → 레포지토리 → Settings → Webhooks → Add webhook.
Payload URL: http://<JENKINS_IP>/github-webhook/
Content type: application/json
Trigger: Just the push event 선택.
로컬 네트워크 문제 해결:

Jenkins가 로컬 네트워크에 있는 경우 ngrok을 사용해 외부 접근 허용:
ngrok http 8080  # Jenkins 기본 포트(8080) 터널링

생성된 ngrok URL을 GitHub 웹훅 URL로 사용.
4. 고급 설정 예시
   (1) 빌드 버전 자동화
   Git 커밋 해시로 APK 버전명 설정:
   android {  
   defaultConfig {  
   versionName "1.0.${System.getenv('BUILD_NUMBER')}"  
   }  
   }

Jenkins 환경 변수 BUILD_NUMBER를 빌드 시 자동 주입.
(2) 빌드 실패 알림
Slack 알림 설정:
플러그인 설치: Slack Notification.
Manage Jenkins → Configure System → Slack 섹션에서 웹훅 URL 입력.
Post-build Actions → Slack Notifications 추가 → Notify Every Failure 체크.
5. 최종 검증
   수동 빌드 테스트:
   Jenkins Job → Build Now 클릭 → 콘솔 출력에서 에러 확인.
   자동 빌드 테스트:
   Git 레포지토리에 푸시 → 5초 내 Jenkins 빌드 자동 시작.
   APK 확인:
   빌드 완료 후 Workspace 또는 아티팩트에서 APK 다운로드.
   문제 해결 팁
   빌드 실패 시:
   Console Output에서 로그 확인 → Permission denied 오류는 chmod +x gradlew로 해결.
   웹훅 미동작 시:
   GitHub → Webhooks → Recent Deliveries에서 응답 상태 확인 (200 성공 필수).
   APK 서명 오류:
   keytool -list -v -keystore keystore.jks로 keystore 정보 재확인.
