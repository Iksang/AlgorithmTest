package kr.co.tjeit.algorithmtest;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class Test06Activity extends BaseActivity {

    private android.widget.TextView remainMoneyTxt;
    private android.widget.TextView myNum01;
    private android.widget.TextView myNum02;
    private android.widget.TextView myNum03;
    private android.widget.TextView myNum04;
    private android.widget.TextView myNum05;
    private android.widget.TextView myNum06;
    private android.widget.TextView okNum01;
    private android.widget.TextView okNum02;
    private android.widget.TextView okNum03;
    private android.widget.TextView okNum04;
    private android.widget.TextView okNum05;
    private android.widget.TextView okNum06;
    private android.widget.Button startBtn;

    // Handler ? => 안드로이드에서 어떤 업무를 수행해주는 프로세스
    // 프로세스 ? => 가상의 CPU
    // CPU? 연산을 처리해주는 역할
    // 일반적인 프로그래밍에서는 Thread.
    // Thread 예시 -> 컴퓨터로 노래 / 게임 / 유튜브. => OS (Window)

    Handler mHandler = new Handler();
    int remainMoney = 10000000;
    long earnMoney = 0;
    int[] mylottoNumber = {1, 4, 8, 28, 33, 42};
    int[] correctlottoNumber = new int[6];
    int bonusNumber = 0;
    private TextView earnMoneyTxt;
    private TextView bonusNumberTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test06);


        bindViews();
        setupEvents();
        setValues();

    }

    // Runnable -> Handler가 수행하게 될 작업 내역
    // Runnable 내부의 run메쏘드에 실제로 수향할 작업을 적으면 된다.

    // OnClickListener 내부의 onClick메쏘드에 실제로 눌리면 할 일을 적는것과
    // 코드의 구조가 거의 유사하고, 이해하는 방식도 거의 같다.

    Runnable doLottonRun = new Runnable() {
        @Override
        public void run() {
            // 실제로 동작되는 부분,
            // Handler가 Runnable을 실행해줌


            //아직 남은 돈이 있는지 검사
            if (remainMoney > 0) {
                // 남은 돈이 있다면

                // 일단, 1000원을 소모한다.
                remainMoney -= 1000;

                // 남은 금액을 컴마를 붙여가며 String으로 만들어준다.
                String moneyStr = String.format(Locale.KOREA, "%,d원", remainMoney);
                // 남은 금액을 화면에 표시
                remainMoneyTxt.setText(moneyStr);

                // 로또 번호 생성
                for (int i = 0; i < 6; i++) {
                    // 1~45 숫자 여섯개를 만들면 된다. 랜덤으로
                    // Math.random 활용하자.

                    // 0 ~ 44 => +1 => 1 ~ 45
                    while (true) {
                        correctlottoNumber[i] = (int) (Math.random() * 45) + 1;

                        boolean isDupl = false;
                        for (int j = 0; j < i; j++) {
                            if (correctlottoNumber[i] == correctlottoNumber[j]) {
                                isDupl = true;
                                break;
                            }
                        }
                        if (!isDupl) {
                            break;
                        }
                    }
                }

//                숫자를 오름차순으로 정렬 => bubble sort
                for (int i = correctlottoNumber.length; i > 0; i--) {
                    for (int j = 0; j < i - 1; j++) {
                        if (correctlottoNumber[j] > correctlottoNumber[j + 1]) {
                            int temp = correctlottoNumber[j];
                            correctlottoNumber[j] = correctlottoNumber[j + 1];
                            correctlottoNumber[j + 1] = temp;
                        }
                    }
                }

//                보너스번호 생성.
                while (true) {
                    bonusNumber = (int) (Math.random() * 45) + 1;

                    boolean isDupl = false;
                    for (int i = 0; i < 6; i++) {
                        if (bonusNumber == correctlottoNumber[i]) {
                            isDupl = true;
                            break;
                        }
                    }
                    if (isDupl) {
                        break;
                    }

                }


                okNum01.setText(correctlottoNumber[0] + "");
                okNum02.setText(correctlottoNumber[1] + "");
                okNum03.setText(correctlottoNumber[2] + "");
                okNum04.setText(correctlottoNumber[3] + "");
                okNum05.setText(correctlottoNumber[4] + "");
                okNum06.setText(correctlottoNumber[5] + "");

                bonusNumberTxt.setText(bonusNumber+"");


                // 번 돈을 계산하는 부분
                // 몇개의 숫자를 맞췄는지 카운팅.
                int okNumberCount = 0;

                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 6; j++) {
                        if (mylottoNumber[i] == correctlottoNumber[j]) {
                            okNumberCount++;
                        }
                    }
                }

                // 맞춘 갯수에 따라 얼마를 벌었는지 계산
                if (okNumberCount == 6) {
                    earnMoney += 2900000000L;
                    return;

                } else if (okNumberCount == 5) {

                    // 숫자 5개를 맞췄을때
                    // 보너스 번호의 유무에 따라 2등/ 3등 구별.

                    boolean bonusOk = false;
                    // 실제로 보너스를 맞췄는지 검사.

                    for(int i = 0 ; i < 6 ; i++){
                        if(mylottoNumber[i] == bonusNumber){
                            bonusOk=true;
                            break;
                        }

                    }
                    if(bonusOk) {
                        earnMoney +=65000000;
                    }
                    else{
                        earnMoney += 1650000;
                    }

                } else if (okNumberCount == 4) {
                    earnMoney += 50000;

                } else if (okNumberCount == 3) {
//                    earnMoney += 5000;
                    remainMoney += 5000;
                }

                String earMoneyStr = String.format(Locale.KOREA, "%,d원 획득!", earnMoney);
                earnMoneyTxt.setText(earMoneyStr);


                // mHandler에게, doLottoRun을 다시 실행시켜 달라고 부탁.
                // 1000원을 다시 쓰도록 만들자.
                if(okNumberCount != 6){
                    mHandler.post(doLottonRun);
                }
            }
        }
    };


    @Override
    public void setupEvents() {

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.post(doLottonRun);
            }
        });

    }

    @Override
    public void setValues() {
        String startMoneyStr = String.format(Locale.KOREA, "%,d원", remainMoney);
        remainMoneyTxt.setText(startMoneyStr);

    }

    @Override
    public void bindViews() {
        this.startBtn = (Button) findViewById(R.id.startBtn);
        this.earnMoneyTxt = (TextView) findViewById(R.id.earnMoneyTxt);
        this.bonusNumberTxt = (TextView) findViewById(R.id.bonusNumberTxt);
        this.okNum06 = (TextView) findViewById(R.id.okNum06);
        this.okNum05 = (TextView) findViewById(R.id.okNum05);
        this.okNum04 = (TextView) findViewById(R.id.okNum04);
        this.okNum03 = (TextView) findViewById(R.id.okNum03);
        this.okNum02 = (TextView) findViewById(R.id.okNum02);
        this.okNum01 = (TextView) findViewById(R.id.okNum01);
        this.myNum06 = (TextView) findViewById(R.id.myNum06);
        this.myNum05 = (TextView) findViewById(R.id.myNum05);
        this.myNum04 = (TextView) findViewById(R.id.myNum04);
        this.myNum03 = (TextView) findViewById(R.id.myNum03);
        this.myNum02 = (TextView) findViewById(R.id.myNum02);
        this.myNum01 = (TextView) findViewById(R.id.myNum01);
        this.remainMoneyTxt = (TextView) findViewById(R.id.remainMoneyTxt);


    }
}
