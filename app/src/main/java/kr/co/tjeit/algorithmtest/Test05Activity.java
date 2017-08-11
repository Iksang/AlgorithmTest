package kr.co.tjeit.algorithmtest;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import kr.co.tjeit.algorithmtest.adapter.ChattingAdapter;
import kr.co.tjeit.algorithmtest.data.ChattingData;

public class Test05Activity extends BaseActivity {

//    1. 야구 게임의 문제는 고정되어 있고, S/B 판별하는 로직 짜서

    int questionNumber = 471; // 이숫자를 맞추는 로직을 짜보자
    // 문제 숫자가 바뀌더라도 대응가능한 채점 로직을 만들자.

    int[] questionNumberArray = {4,7,1}; // 출제된 문제를 세자리 배열로 분할.
    // 한자리씩 비교하기에 편하게 하려고 만든 배열.

    int strikeCount = 0; // 문제를 입력하면 계산되는 스트라이크의 개수
    int ballCount = 0; // 문제를 입력하면 계산되는 볼의 개수

    int tryCount = 0;



//    2. 사용자에게 몇S 몇B 채팅 메세지로 던져줌
//    3. 몇번 시도했는지 세서, 사용자에게 "현재까지 %d회 시도했습니다." 메세지 전송
//    4. 만약 3S 축하합니다! 클리어했습니다! => EditText 입력 / 전송 버튼 막는것.

    private android.widget.ListView chattingListView;
    private android.widget.EditText inputEdt;
    private android.widget.Button sendBtn;

    List<ChattingData> chattingDatas = new ArrayList<>();
    ChattingAdapter mAdpater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test05);
        bindViews();
        setupEvents();
        setValues();

    }

    @Override
    public void setupEvents() {
        // 화면에서 사용자가 터치로 인해 발생하는 모든 이벤트 코드를 작성




        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();

            }
        });



    }

    private void checkAnswer() {
        // 1. 전송버튼을 누르면 Toast로 데이터를 입력합니다.
//        Toast.makeText(mContext, "데이터를 입력합니다.", Toast.LENGTH_SHORT).show();

        // 2. 전송버튼을 누르면 EditText의 내용을 비움.
        final String inputStr = inputEdt.getText().toString();
        inputEdt.setText("");

        // 3. 전송버튼을 누르면, 내가 입력한 내용이 리스트뷰에 나타나게.
        // => 선행작업.

        // adapter에 물려 있는 list에 어떤 클래스들이 추가/삭제 되느냐에 따라
        // 리스트뷰에 나타나는 내용이 영향을 받는다.
        // 추가/삭제 하면 반드시 refresh 해줘야함. -> 변경내용 반영.
        // mAdpater.notifyDataSetChanged();

        // 반대로 보면, 내가 입력한게 리스트뷰에 나타나려면
        // 어댑터에 물려있는 리스트에 적절한 데이터를 추가하고 새로고침 하면된다.
        chattingDatas.add(new ChattingData(true, inputStr));
        mAdpater.notifyDataSetChanged();

        // 5. 3자리 숫자 까지만 입력을 받을 수 있게 EditText를 설정
        // => 문자 입력금지, 4자리 숫자 입력 금지.


        // ※ 지금은 채팅을 입력하면 컴퓨터가 즉시 답장해주는데.
        // 내가 말한건 바로 뜨고, 그로부터 0.5~1초 정도 후에 컴퓨터가 답장하도록
        // 어려울 수 있다.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // 0.7초후에 할일은?
                // 컴퓨터가 답장을 해주고
                // 스크롤을 끌어 내리는것

                // 6. 숫자를 넣으면, 무조건 컴퓨터가 "확인했습니다." 답장해주는 시스템.
                chattingDatas.add(new ChattingData(false, "확인했습니다."));

//                7. 숫자를 넣으면, 몇S 몇B인지 계산해서 답장해주는 시스템.

                // strikeCount, ballcount의 개수를 몇개인지 계산.

                strikeCount = 0;
                ballCount = 0;
                strikeBallCounter(Integer.parseInt(inputStr));

                tryCount++;

                String resultStr = String.format(Locale.KOREA,"%d 스트라이크, %d 볼입니다. \n%d번 시도됐습니다.", strikeCount,ballCount,tryCount);
                chattingDatas.add(new ChattingData(false, resultStr));


                // tryCount를 활용하여 "%d번 시도됐습니다. " 메시지 던져주기
//                resultStr = String.format(Locale.KOREA,"%d번 시도됐습니다. ", tryCount);
//                chattingDatas.add(new ChattingData(false, resultStr));

                // 3S인 경우 "축하합니다. 클리어하셨습니다." 메세지 던지고
                // EditText와 버튼을 비활성화
                if(strikeCount==3){
                    chattingDatas.add(new ChattingData(false, "축하합니다. 클리어하셨습니다"));
                    inputEdt.setEnabled(false);
                    sendBtn.setEnabled(false);
                }


                mAdpater.notifyDataSetChanged();
                chattingListView.smoothScrollToPosition(mAdpater.getCount() - 1); //부드럽게 이동
            }
        }, 2000);




        // 4. 입력한 내용이 맨 아래에 달리니, 바로 스크롤을 맨 밑으로 끌어내려서
        // 입력한 내용을 바로 읽어볼 수 있게.
//        chattingListView.setSelection(mAdpater.getCount() - 1);
        chattingListView.smoothScrollToPosition(mAdpater.getCount() - 1); //부드럽게 이동





    }

    private void strikeBallCounter(int inputNumber) {
        int[] inputNumberArray = new int[3];
//        for(int i = 2 ; i >= 0 ; i--){
//            inputNumberArray[i] = inputNumber % 10;
//            inputNumber /=10;
//        }

        inputNumberArray[0] = inputNumber / 100;
        inputNumberArray[1] = inputNumber % 100 / 10;
        inputNumberArray[2] = inputNumber % 10;


        for(int i = 0 ; i < 3 ; i++){
            for(int j = 0 ; j < 3 ; j++){
                if(questionNumberArray[i]==inputNumberArray[j]){
                    if(i==j){
                        strikeCount++;
                    }
                    else{
                        ballCount++;
                    }

                }
            }

        }
    }

    @Override
    public void setValues() {
        // 화면에 데이터를 뿌려주는 모든 코드를 작성.

        chattingDatas.clear();

//        리스트뷰 - Adapter 연결

//        1. Adapter를 객체화
        mAdpater = new ChattingAdapter(mContext, chattingDatas);;
//        객체화 : 거의 대부분 new, 가끔식 static메쏘드로 from, getInstacne.


//        2. 객체화 된 어댑터와, 화면에 그려진 리스트뷰를 setAdapter로 연결
//        리스트뷰.setAdapter(객체화된 어댑터);

        chattingListView.setAdapter(mAdpater);
        makeQuestion();
    }

    public void makeQuestion(){

//        questionNumber 와 questionNumberArray
//        문제를 랜덤으로 출제 하도록.

//        1. 문제를 Math.Random -> 무작위 세자리 숫자. 100~999
//          확인 : 471을 입력했을때 정답이 아닌걸로
//          Log를 이용해 문제가 무엇으로 출시됐는지.
        Log.d("랜덤",(Math.random()*900+100)+"");

//        2. 출제된 문제에 제약조건. 조건이 안맞으면 다시 출제.
//         1) 숫자에 0이 들어가면 안됨.
//         2) 모든 자리의 숫자는 서로 달라야함.
        boolean checkZero = false;
        boolean checkDupl = false;
        while(true){
            Log.d("랜덤",questionNumber+"");
            questionNumber =(int)(Math.random()*900+100);
            checkZero = questionNumber / 100 != 0 && questionNumber % 100 / 10 != 0 && questionNumber % 10!=0;
            checkDupl = questionNumber / 100 != questionNumber % 100 / 10 &&
                    questionNumber % 100 / 10 != questionNumber % 10 &&
                    questionNumber / 100 != questionNumber % 10;
            if(checkZero &&checkDupl){
                questionNumberArray[0] = questionNumber / 100;
                questionNumberArray[1] = questionNumber % 100 / 10;
                questionNumberArray[2] = questionNumber % 10;
                break;
            }

        }




    }



    @Override
    public void bindViews() {
        this.sendBtn = (Button) findViewById(R.id.sendBtn);
        this.inputEdt = (EditText) findViewById(R.id.inputEdt);
        this.chattingListView = (ListView) findViewById(R.id.chattingListView);

    }


}
