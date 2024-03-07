# kotlin-blackjack

### 클래스 다이어그램
![Section 2.png](image%2FSection%202.png)

# 기능목록

### Model

---
- 카드
  - 카드 목록은 ACE부터 KING까지 총 13장으로 구성되어 있다.
    - [x] A(1,11), J(10), Q(10), K(10)는 각각 괄호의 숫자로 반환된다.
    - [x] 그 이외의 숫자는 그 숫자로 반환된다.
  - 각 카드는 4가지 문양을 가지고 있다.
    - [x] 스페이드, 다이아, 하트, 클로버로 구성된다. 
  - [x] 카드는 카드 문양과 숫자의 조합을 반환한다.
- 카드 더미
  - 카드 더미는 총 52장의 카드로 구성되어 있다.
    - [x] ACE 부터 KING까지 13개의 숫자와 4가지 문양을 한 더미로 가지고 있다.
    - [x] 플레이어와 딜러는 카드 더미에서 카드를 드로우 할 수 있다.
---
- 손패
  - 게임의 참여자는 카드 더미에서 뽑힌 카드를 손패로 가지고 있다.
    - [x] 손패는 카드들의 숫자 총합을 반환한다.
- 블랙잭
  - 블랙잭 모델은 손패와 참여자의 상태를 나타내는 state를 가지고 있다.
    - [x] 블랙잭에서는 손패의 점수를 판단하여 state를 변환한다.
      - 플레이어 손패의 합이 21이 넘는다.
        - [x] 플레이어가 ACE를 가지고 있는 경우 ACE를 1로 전환하고 state를 재설정한다.
        - [x] 플레이어가 ACE를 가지고 있지 않는다면 `버스트` 상태로 변환한다.
      - [x] 플레이어는 합이 21 미만이면 `히트` 또는 `스테이` 상태를 결정할 수 있다.
      - [x] 플레이어는 손패의 합이 21이라면 블랙잭 상태가 된다.
---
- State
  - state는 참여자의 진행 상태를 나타낸다.
  - 상태는 `액션` 과 `피니시`로 나눠진다.
    - [x] 액션은 `히트` 상태를 가지고 있다.
    - [x] 피니쉬는 `버스트` , `스테이` , `블랙잭` 상태를 가지고 있다.
---
- 플레이어
  - 플레이어는 이름이랑 블랙잭을 가지고 있다.
    - [x] 이름은 중복 될 수 없다.
- 딜러
  - 딜러는 이름과 블랙잭을 가지고 있다.
    - [x] 딜러의 이름은 생성자로 구현된다. (딜러)
  - [x] 딜러는 2장 중 첫번째 카드만 공개한다.
  - [x] 딜러의 손패의 합이 16을 초과할 때 까지 카드를 새로 받는다.
- 참여자
  - [x] 참여자는 딜러 혹은 플레이어가 된다.
- 참여자들
  - [x] 참여자들은 참여자 목록을 가지고 있다.
  - [x] 게임 플레이어는 최대 8명이다.
---
- Manager
  - 게임 매니저는 게임 규칙을 관리한다.
    - [x] 딜러와 플레이어는 게임이 시작되면 차례대로 한 장씩 2장의 카드를 받는다.
    - [x] 플레이어들은 `피니시` 상태가 아니라면 카드를 더 받을 수 있다.
    - [x] 플레이어들은 `액션` 상태라면 카드 추가 여부를 결정할 수 있다.
- UserDecision
  - UserDecision은 사용자의 진행 여부를 판단한다.
    - [ ] 잘못 된 입력이 들어오면 에러 처리한다.
    - [ ] 유저의 입력에 따른 UserDecision을 반환한다. 
- Result
  - 결과는 게임 결과를 관리한다.
  - 결과는 참여자들을 가지고 있다.
    - [ ] 참여자의 상태를 통해 게임 결과를 관리한다.
    - 참여자들의 블랙잭 결과에 따라 딜러와 플레이어의 승패를 결정한다.
      - [ ] state를 비교해서 `버스트` 참여자를 `패배` 처리한다.
      - [ ] state를 비고해서 딜러가 버스트 라면 `버스트 되지 않은` 참가자는 `승리` 처리한다. 
      - [ ] 딜러의 점수를 기준으로 플레이어의 승패를 판단한다.
        - [ ] 딜러보다 낮은 플레이어를 패배 처리한다.
        - [ ] 딜러와 같은 플레이어를 무승부 처리한다.
        - [ ] 딜러보다 높은 플레이어를 승리 처리한다.

### View

---
- InputView
  - [ ] 게임에 참여할 플레이어의 이름을 받는다.
  - [ ] 각 플레이어의 카드 드로우 진행 여부를 받는다.
---
- OutputView
  - [ ] 각 2장의 카드를 나눴다는 멘트를 출력한다.
  - [ ] 딜러의 손패를 출력한다.
  - [ ] 각 플레이어의 손패를 출력한다.
  - [ ] 드로우 여부에 따른 플레이어의 손패를 출력한다.
  - [ ] 딜러의 손패가 16이하라면 추가 문구를 입력 받았다는 멘트를 출력한다.
  - [ ] 딜러의 블랙잭 결과 출력한다.
  - [ ] 각 플레이어의 블랙잭 결과를 출력한다.
  - [ ] 게임 결과를 출력한다.

### Controller

---
- [ ] 게임 시작 플로우를 진행한다.
- [ ] 게임을 진행한다.
- [ ] 게임 결과를 계산한다.
- [ ] 게임 결과를 출력한다.
