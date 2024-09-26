# [MUSINSA] Java Backend Engineer 과제


## 1. 개발환경
- **프레임워크**: Spring Boot 3.3.4
- **언어**: Java 17
- **빌드 도구**: Gradle
- **데이터베이스**: H2 (로컬)
- **API 문서화**: Swagger

## 2. 코드 빌드, 테스트, 실행 방법

### 2.1. 빌드 및 테스트
```bash
./gradlew clean build
```

> **참고**: Builder warning이 뜰경우 다시한번 build하면 해결할 수 있습니다.

### 2.2. 애플리케이션 실행
```bash
./gradlew bootRun
```

위 명령어를 사용하면 **Spring Boot** 애플리케이션이 실행됩니다. 실행 후 `http://localhost:8080`에서 API를 사용할 수 있습니다.

### 2.4. H2 데이터베이스 접속
애플리케이션 실행 중에 H2 콘솔을 통해 데이터베이스에 접근할 수 있습니다.
- **H2 콘솔**: `http://localhost:8080/h2-console`
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **db username**: `sa`
- **db password**: `sa`

### 2.5. API 문서 및 테스트
실행 후, API 문서를 확인하고 테스트 할 수 있습니다.
- **OpenAPI UI**: `http://localhost:8080/swagger-ui/index.html#/`

## 3. 구현 범위에 대한 설명

### 3.1. 주요 기능
- **상품 관리**: 상품 생성, 수정, 삭제 기능을 제공하며 상품 정보 변경 시 가격 계산 및 갱신을 처리합니다.
- **브랜드 관리**: 브랜드 생성, 수정, 삭제 기능을 제공하며 브랜드 삭제 시 관련된 최저가/최고가 정보를 처리합니다.
- **가격 관리**: 상품의 가격 변경 시 최저가 및 최고가 정보를 갱신합니다.
- **카테고리별 최저가 조회**: 카테고리별 최저가 가격과 브랜드를 조회합니다.
- **최저가 브랜드 및 카테고리 조회**: 카테고리별 최저가 합산 금액이 가장적은 브랜드를 조회합니다.
- **브랜드별 카테고리 최저가, 최고가 조회**: 카테고리를 검색했을때 최저가격 브랜드와 최고가격 브랜드를 조회합니다.


## 4. 트러블슈팅 : 상품 데이터가 많아질 경우의 성능 문제 및 동기화 처리

### 문제 상황

1. **최고가/최저가 API 조회 시 트래픽 증가로 인한 성능 문제**:
    - 상품 데이터가 많아지면, 트래픽이 증가할 경우 API의 성능 저하가 발생할 수 있습니다. 특히 실시간으로 가격을 계산해야 할 때 서버에 부하가 생길 수 있습니다.

2. **브랜드 삭제 시 소프트 삭제로 인한 성능 및 동시성 문제**:
    - 브랜드를 삭제할 때 해당 브랜드의 상품을 소프트 삭제 처리합니다. 이로 인해 상품 정보 업데이트 시 성능 저하 및 동시성 문제가 발생할 수 있다고 판단했습니다.

### 설계 접근 방식

#### 1. Batch or Scheduler 작업
- **개요**: 배치 작업을 통해 주기적으로 최저가 및 최고가를 미리 계산해 DB에 저장하는 방식.
- **장점**:
    - 데이터베이스 부하 감소: 실시간 계산을 줄이고 트래픽이 많을 때 DB에 대한 부담을 줄일 수 있습니다.
    - API 성능 개선: API 호출 시 별도의 계산 작업 없이 즉시 응답하여 성능이 개선됩니다.
- **단점**:
    - 데이터 일관성 문제: 실시간으로 데이터가 반영되지 않기 때문에 일관성 문제가 발생할 수 있습니다.

#### 2. 최저가/최고가 전용 테이블 생성
- **개요**: 상품의 최저가와 최고가를 저장하는 전용 테이블을 생성하고, API 호출 시 해당 테이블을 참조하는 방식.
- **장점**:
    - API 성능 개선: 모든 상품을 계산할 필요 없이, 최저가/최고가 테이블에 저장된 소수의 데이터를 참조해 빠르고 효율적인 응답을 제공할 수 있습니다.
    - 데이터베이스 부하 감소: 실시간으로 모든 상품의 가격을 계산하지 않기 때문에, DB 부하가 줄어듭니다.
- **단점**:
    - 추가적인 쓰기 작업: 상품 추가, 수정, 삭제 시마다 최저가/최고가 테이블을 갱신해야 하므로 쓰기 작업이 증가합니다.
    - 데이터 일관성 문제: 최저가/최고가 상품이 삭제될 경우 실시간으로 이를 반영하는 추가 로직이 필요합니다.

#### 3. 쿼리를 통한 실시간 계산
- **개요**: API 호출 시 실시간으로 쿼리를 통해 최저가 및 최고가를 계산하는 방식.
- **장점**:
    - 데이터 일관성 보장: 실시간으로 계산하여 항상 최신 데이터를 제공합니다.
    - 구현이 간단: 별도의 테이블 없이 쿼리만으로 처리하여 코드가 단순해집니다.
- **단점**:
    - 대용량 데이터 처리 문제: 데이터가 많아질수록 쿼리 성능이 저하될 수 있으며, 대규모 트래픽 시 API 응답이 느려질 수 있습니다.

#### 4. 캐싱(Caching)
- **개요**: 자주 호출되는 최저가/최고가 API 응답을 캐싱하여 성능을 개선.
- **장점**:
    - 트래픽 감소: 동일한 요청에 대해 캐시된 데이터를 반환하여 서버 부하를 줄일 수 있습니다.
    - 응답 시간 단축: 캐시에서 즉시 데이터를 제공하여 응답 시간을 단축할 수 있습니다.

#### 5. 메시지 큐(Message Queue, Kafka)
- **개요**: 상품이 추가되거나 삭제될 때 최저가/최고가를 계산하는 비동기 처리를 위해 메시지 큐를 사용.
- **장점**:
    - 비동기 처리로 인해 성능 향상: 즉시 응답하고, 백그라운드에서 작업을 처리하여 서버 부하를 분산할 수 있습니다.
    - 확장성: 시스템 확장 시 메시지 큐를 통해 분산 처리할 수 있어 성능이 더욱 향상됩니다.
- **단점**:
    - 복잡도 증가: 로컬 DB 환경에서는 메시지 큐 도입이 오버엔지니어링이 될 수 있다고 판단했습니다.

### 최종 선택: 최저가/최고가 테이블 전략 + AOP를 통한 가격 정보 갱신 처리

최종적으로 선택한 전략은 **최저가/최고가 전용 테이블**을 사용하는 방식입니다. 이를 통해 API 성능을 개선하고, 실시간 계산을 줄임으로써 데이터베이스 부하를 줄였습니다.

그러나 상품의 추가, 수정, 삭제 및 브랜드 삭제 시 **최저가/최고가 테이블을 실시간으로 갱신**하는 문제가 남았습니다. 이를 해결하기 위해 **AOP(Aspect-Oriented Programming)**를 적용하여 상품이 변경되거나 브랜드가 삭제될 때마다 자동으로 최저가/최고가 테이블을 갱신하는 로직을 추가했습니다.

### AOP를 통한 가격 정보 갱신 처리

1. **비즈니스 로직과 동기화 로직의 분리**:
    - 상품 및 브랜드 관련 로직과 가격 정보를 갱신하는 로직을 AOP로 분리함으로써 코드의 유지보수성을 높였습니다.
    - 동기화 로직을 코드 곳곳에 작성할 필요 없이 AOP를 통해 중앙에서 관리할 수 있게 되어 유지보수가 용이해졌습니다.

2. **트랜잭션과의 통합**:
    - AOP를 통해 가격 갱신 로직을 트랜잭션 내에서 처리하여, 트랜잭션 오류가 발생하면 가격 동기화 작업도 함께 롤백될수있게 처리하였습니다.

3. **중복 로직 제거**:
    - 상품 또는 브랜드 삭제 시 발생하는 중복적인 동기화 로직을 AOP로 통합하여 처리하므로, 코드 중복을 방지하였습니다.

### 갱신 로직 설명

상품의 추가, 수정, 삭제 시 **최저가 및 최고가를 자동으로 갱신**하는 로직을 통해, 상품 가격 정보의 **일관성**을 유지하고 **API 성능을 최적화**했습니다. 주요 로직은 다음과 같습니다:

1. **최저가 갱신**:
    - 새로 등록된 상품이 현재 카테고리 및 브랜드의 최저가보다 낮은 경우, 해당 상품을 최저가 테이블에 갱신합니다.
    - 만약 최저가 상품이 없을 경우, 새로운 상품이 최저가로 등록됩니다.

2. **최고가 갱신**:
    - 새로 등록된 상품이 현재 카테고리 및 브랜드의 최고가보다 높은 경우, 해당 상품을 최고가 테이블에 갱신합니다.
    - 최고가 상품이 없을 경우, 새로운 최고가 상품으로 추가됩니다.

- **캐시 갱신 처리**:
    - 기존 로직에서는 최저가 또는 최고가 상품이 변경되었을 경우 조회 API 캐시 갱신을 하려 했으나, 구현 시간 부족으로 인해 캐시 적용을 하지 못했습니다. 

> **참고**: Spring에서 제공하는 캐시를 활용해 성능을 최적화하려 했으나, 현재는 캐시 적용이 되어 있지 않습니다.

### 결론

최저가/최고가 테이블 전략을 통해 API 성능을 개선했으며, AOP를 통해 상품 및 브랜드 변경 시 가격 정보를 자동으로 갱신함으로써 데이터 일관성을 유지하고 코드 효율성을 높였습니다.