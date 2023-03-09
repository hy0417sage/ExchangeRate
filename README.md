# ExchangeRate
### 조건
1. 송금국가는 미국으로 고정입니다. 통화는 미국달러(USD)입니다.
2. 수취국가는 한국, 일본, 필리핀 세 군데 중 하나를 여러가지 방법을 이용하여 선택합니다. 각각 통화는 KRW, JPY, PHP 입니다.
3. 수취국가를 선택하면 아래 환율이 바뀌어나타나야 합니다. 환율은 1 USD 기준으로 각각 KRW, JPY, PHP의 대응 금액입니다.
4. 송금액을 USD로 입력하면 아래 수취금액이 KRW, JPY, PHP 중 하나로 계산되어서 나와야 합니다.
5. 환율과 수취금액은 소숫점 2째자리까지, 3자리 이상 되면 콤마를 가운데 찍어 보여줍니다. 예를 들어 1234라면 1,234.00으로 나타냅니다.
6. 환율은 앱이 시작될 때 한번 가져와서 계속 사용해도 되고, 혹은 수취국가가 변경될때 마다 API로 서버에 요청해서 새로운 환율 정보를 가져와도 됩니다.
7. 송금액을 입력할때마다 수취금액을 계산하거나 별도로 '계산하기'' 버튼을 추가하여 선택된 수취국가와 그 환율, 그리고 송금액을 가지고 수취금액을 계산해서 하단에 보여주면 됩니다.
8. 수취금액이 0보다 작은 금액이거나 10,000 USD 보다 큰 금액, 혹은 바른 숫자가 아니라면 “송금액이 바르지 않습니다"라는 에러 메시지를 보여줍니다. 메시지는 팝업, 혹은 하단에 빨간 글씨로 나타나면 됩니다.
