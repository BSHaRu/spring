## Context 사전적 "context의 사전적 정의는 맥락, 전후사정 , 문맥:서로 이어져 있는 문장의 앞뒤 관계."

context라는 말은 여러군데에서 사용됩니다.
보통 컴에서 context 는 cpu 의 레지스터값들을 말합니다.
execution context라는 개념은 보통 루틴의 실행(execution)에 영향을 줄 수 있는
register, call stack, memory 등의 상태를 말합니다.
어떤 것에 영향을 주는 외부적인 것들은 다 context라고 불릴 수 있습니다.

### 예시 
Let's say you go to the dentist to have a tooth pulled out.
-> 당신이 이빨을 뽑으러 치과에 간다고 해봅시다.

When the receptionist asks you for your name, that's information they need in order to begin the appointment. In this example, your name is contextual information. So in the context of visiting the dentist, you need to provide your name to get your tooth pulled.
-> 접수원이 당신에게 이름을 묻는다면, 그것은 접수를 시작하기 위해 필요한 정보들일 것입니다. 
      앞의 예에서는 당신의 이름은 context적인 정보가 되겠죠.  치과를 방문한다는 context에서 당신은 이빨을 뽑기위해 당신의 이름을 제공해야하는거죠 
      
Now let's say you walk over to the bank.
->이번엔 은행에 간다고 해봅시다.

At the bank, you ask to withdraw $100. The teller needs to establish your identity before giving you money, so you'll probably have to show them a driver's license or swipe your ATM card and enter your PIN number. Either way, what you're providing is context. The teller uses this information to move the transaction forward. They may then ask you which account you'd like to withdraw from. When you answer, "My savings account", that's even more context.
-> 은행에서 당신은 100달러를 인출하려 합니다. 은행원은 당신에게 돈을 주기전에 당신의 정체성을 확립해야하므로, 아마 당신은 그들에게 운전면허증을 보여주거나, ATM카드를 긁거나 PIN 번호를 입력해야 할 것입니다. 이와 같이 당신이 제공하는 것이 context입니다. 은행원은 받은 정보를 통해 일을 다음단계를 진행합니다. 은행원은 당신에게 어떤 계좌에서 인출할 것인지 물어볼 것입니다. 그때 당신이 “내 예금 계좌요” 라고 대답한다면, 그것은 추가적인 context가 됩니다. 

The more context you give, the more knowledge the other party has to help deal with your request. Sometimes context is optional (like typing more and more words into your Google search to get better results) and sometimes it's required (like providing your PIN number at the ATM). Either way, it's information that usually helps to get stuff done.
-> 더 많은 context를 당신이 제공할수록, 더 많은 지식을 다른 편에 제공할수록, 상대방이 당신의 요청에 대처하는데 도움을 주는 것입니다. 어떨 때는 context는 선택적입니다. (구글 검색에서 더 많은 단어를 입력할수록 더 정확한 결과가 나온다.) 그리고 때때는 필수적입니다(ATM에 PIN 번호를 입력한는 것) 어떤 방법이든 context의 정보는 일이 진행되는 도움을 주게됩니다.

Now let's say you take your $100 and buy a plane ticket to fly somewhere warm while your mouth heals.
-> 이제 100달러를 가지고 당신이 비행기 표를 끊어 입안이 낫는 동안 어디 따뜻한 곳으로 놀러간다고 해봅시다.

You arrive at a nice sunny destination, but your bag doesn't make it. It's lost somewhere in the airport system. So, you take your "baggage claim ticket" (that sticker with the barcode on it) to the "Lost Baggage office". The first thing the person behind the desk will ask for is that ticket with your baggage number on it. That's an example of some required context.
-> 당신은 화창한 도착지에 도착합니다. 하지만 당신의 가방은 그러지 못합니다. 가방은 공항 시스템 어디엔가에서 길을 잃은 거죠. 그래서 당신은 “짐 요청 티켓”(바코드가 찍힌 스티커)를 들고 “분실물 센터”로 갑니다. 담당자는 가장먼저 당신에게 짐의 바코드가 적힌 티켓을 요구할겁니다. 이게 필수 context의 한 예입니다.

 But then the baggage person asks you for more information about your bag like so they can find it more easily. They ask, "What color is it? What size is it? Does it have wheels? Is it hard or soft?While they don't necessarily need those pieces of information, it helps narrow things down if you provide them. It reduces the problem area. It makes the search much faster. That's optional context.
 -> 그러나 분실물 센터 담당자는 당신에게 가방에 대한 추가적인 정보를 물어볼 것입니다. 더 쉽게 찾기 위해서죠. 그들은 “색깔은 뭐죠? 크기는 어떻죠? 바퀴가 있나요? 단단한가요 부드럽나요?” 사실 그들은 그런 정보가 필수적 이진 않지만, 제공한다면, 검색 범위를 좁혀주긴 할 테죠. 그러면 더 빨리 찾을 수 있고, 이게 선택적 context입니다.
 
 Here's the interesting part: for a lot of software and APIs, the required context usually ends up as actual parameters in a method signature, and optional context goes somewhere else, like a flexible key-value map that can contain anything (and may be empty) or into thread-local storage where it can be accessed if needed.
 -> 가장 중요한 부분입니다. 많은 소프트웨와 API들에서 함수의 명세의 실제 매개변수로 required context를 요구합니다. Optional context는 다른 곳에서, 유연한 어떤 값도 담을 수 있는(값이 없을 수도 있는)키-값 맵 같은 거나, 필요할 때마다 어디서든 접근할 수 있는 Thread-local storage에서 사용합니다..
 
 The examples above are from real life, but you can easily map them to areas within computer science. For example, HTTP headers contain contextual information. Each header relates to information about the request being made. Or when you're sending along a global transaction ID as part of a two-phase commit process, that transaction ID is context. It helps the transaction manager coordinate the work because it's information about the overall task at hand.
 -> 위의 예제는 현실 세계에서의 얘기이긴 하지만 컴퓨터 공학 분야에도 쉽게 적용할 수 있어요. 예를 들면, HTTP 헤더들은 context적인 정보를 담고 있어요. 각 헤더는 어떤 요청이 발생했는지에 대한 내용을 담고 있죠. 또한한 two-phase commit process 의 일부로 전역 트랜잭션 아이디를 보낸다면, 트랜잭션 아이디는 context가 됩니다. 아이디는 테스크의 전반적인 정보이므로, 트랜젝션 매니저가 작업을 조정하는데 큰 도움이 됩니다.
 
 
### 요약하자면 Context는 어떤 행위가 일어나기 위해서 필수적인 필수 Context, 그 행위를 더욱 효과적으로 할 수 있게해주는 선택적 Context가 있다.
## Context는 어떤 행위(Task. Method..)를 위한 정보의 통칭이다.

 
