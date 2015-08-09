package com.quki.gongmo.bary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

public class SangseFirst extends ActionBarActivity {

	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader; 
	HashMap<String, List<String>> listDataHashMap;
	private int lastExpandedPosition = -1;

	String myId, a, b;
	int p, c;

	@Override
	protected void onStop() {

		int count = listAdapter.getGroupCount();
		for (int i = 0; i < count; i++)
			expListView.collapseGroup(i);
		super.onStop();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sangse_first);
		setTitle("배려지침상세");
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		// get the listview
		expListView = (ExpandableListView) findViewById(R.id.lvExp);

		// preparing list data
		prepareListData();

		listAdapter = new ExpandableListAdapter(this, listDataHeader,
				listDataHashMap);

		// setting list adapter
		expListView.setAdapter(listAdapter);

		expListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {

				// 넘겨줄 변수 함수!! 각각 position+1해주고 0을 붙여서 string값으로
				p = groupPosition + 1;
				c = childPosition + 1;

				if (p < 10) {
					myId = "0" + p + "_" + 0 + c;
				} else {
					myId = p + "_" + 0 + c;
				}

				// intent
				Intent myIntent = new Intent(getApplicationContext(),
						SangseSecond.class);
				myIntent.putExtra("parent", listDataHeader.get(groupPosition));
				myIntent.putExtra("child",
						listDataHashMap.get(listDataHeader.get(groupPosition))
								.get(childPosition));
				myIntent.putExtra("myId", myId);
				startActivity(myIntent);
				return false;
			}
		});
		expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {
				if (lastExpandedPosition != -1
						&& groupPosition != lastExpandedPosition) {
					expListView.collapseGroup(lastExpandedPosition);
				}
				lastExpandedPosition = groupPosition;

			}
		});
		expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {

			}
		});
	}

	/*
	 * Preparing the list data
	 */
	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataHashMap = new HashMap<String, List<String>>();

		// Adding child data
		listDataHeader.add("대체형식활용");
		listDataHeader.add("정보의 위치");
		listDataHeader.add("적절한 조명");
		listDataHeader.add("색상과 대비");
		listDataHeader.add("제어장치정보");
		listDataHeader.add("명확한 언어");
		listDataHeader.add("심벌과 삽화");
		listDataHeader.add("청각 정보");
		listDataHeader.add("정보처리속도");
		listDataHeader.add("형태나 포장");
		listDataHeader.add("취급 용이성");
		listDataHeader.add("유효기간표시");
		listDataHeader.add("위험물질경고");
		listDataHeader.add("표면 온도");
		listDataHeader.add("접근가능경로");
		listDataHeader.add("논리적 순서");
		listDataHeader.add("표면 처리");
		listDataHeader.add("위험한 물질");
		listDataHeader.add("음향 설계");
		listDataHeader.add("안전장치");
		listDataHeader.add("환기");
		listDataHeader.add("화재안전장치");

		// Adding child data
		List<String> listChildData1 = new ArrayList<String>();
		listChildData1.add("대체 형식의 두 가지 접근법");
		listChildData1.add("시각 정보에 촉감 표시와 청각 신호를");
		listChildData1.add("청각 신호의 대안으로는 시각 정보와 촉감 표시를");
		listChildData1.add("음성 입력이나 생체 인식에도 대체 형식 활용");
		listChildData1.add("눈부신 화면이 발작의 원인");
		listChildData1.add("시각 기호와 함께 음성 정보를");

		List<String> listChildData2 = new ArrayList<String>();
		listChildData2.add("정보와 제어장치는 누구나 접근 가능한 곳에");
		listChildData2.add("시각 정보는 시각 영역의 중심부에 배치");
		listChildData2.add("제어장치는 눈에 잘 띄고 손에 쉽게 닿는 곳에");
		listChildData2.add("건물은 설계 시 제어장치의 접근성 고려");
		listChildData2.add("위치뿐만 아니라 배치도 중요");

		List<String> three = new ArrayList<String>();
		three.add("조명의 필요성");
		three.add("조명 수준의 조절");
		three.add("공간 용도와 작업 종류에 따른 조명 수준");
		three.add("눈부심을 피하자");

		List<String> four = new ArrayList<String>();
		four.add("색상은 조건에 따라 달라 보인다");
		four.add("서로 대비되는 색상 조합으로");
		four.add("색상만의 단독 사용은 피할 것");
		four.add("휘도 대비가 가시성을 높인다");

		List<String> five = new ArrayList<String>();
		five.add("글꼴의 종류와 크기가 판독성 결정");
		five.add("휘도가 낮고 가까운 거리에서는 글꼴을 크게");
		five.add("돌출부는 뚜렷하게, 서체는 산세리프체로");
		five.add("디지털 화면에 사용하는 글꼴");

		List<String> six = new ArrayList<String>();
		six.add("사용설명서는 텍스트 형태로");
		six.add("문서는 명확하고 단순한 언어로");
		six.add("구두 발표 시의 음량");
		six.add("음성 설명 시에 필요한 요소");
		six.add("다국어로 쓰인 설명서는 언어별로 분리");
		six.add("문서 내 정보 찾기와 언어 지원");

		List<String> seven = new ArrayList<String>();
		seven.add("심벌과 삽화는 의사소통에 유용한 도구");

		List<String> eight = new ArrayList<String>();
		eight.add("청각 정보를 전달하기 적당한 음량과 높낮이");

		List<String> nine = new ArrayList<String>();
		nine.add("정보 전달은 적당한 속도로");
		nine.add("사용자의 기억력을 고려해야");
		nine.add("시간에 따라 변하는 정보도 따라갈 수 있게");
		nine.add("추가 시간을 요청할 수 있게");

		List<String> ten = new ArrayList<String>();
		ten.add("형태로 식별할 수 있게");
		ten.add("촉감 표시로 방향 제시");
		ten.add("위험물질이 든 용기에는 촉지 경고를");

		List<String> eleven = new ArrayList<String>();
		eleven.add("사용자를 배려한 크기·형태 및 무게");
		eleven.add("사용설명서도 보기 쉽게");
		eleven.add("제어장치는 적은 힘으로 간단히 조작할 수 있게");
		eleven.add("사용하기에 충분한 시간을");
		eleven.add("용기와 포장은 뜯거나 열기 쉽게");
		eleven.add("건물과 시설은 접근하기 쉽고 작동하기 편하게");

		List<String> twelve = new ArrayList<String>();
		twelve.add("식품뿐만 아니라 일반 제품도 유효 기간이 있다");

		List<String> thirteen = new ArrayList<String>();
		thirteen.add("내용물 및 특정 성분 표기");
		thirteen.add("경고와 주의도 명확하게");

		List<String> fourteen = new ArrayList<String>();
		fourteen.add("고령자는 온도 자극에 덜 민감");
		fourteen.add("적절한 온도 환경");

		List<String> fifteen = new ArrayList<String>();
		fifteen.add("지면은 높낮이가 심하지 않게");
		fifteen.add("높낮이 변화가 있으면 승강기나 경사로 설치");
		fifteen.add("층계에는 반드시 손잡이와 난간 설치");
		fifteen.add("바닥은 미끄럽지 않고, 문은 찾기 쉽고 여닫기 편하게");
		fifteen.add("휠체어 공간에는 동반자석을 함께 제공");
		fifteen.add("건물의 경로 정보를 제공");
		fifteen.add("비상 경로의 설계");

		List<String> sixteen = new ArrayList<String>();
		sixteen.add("실행은 논리적인 순서로");
		sixteen.add("피드백 제공은 지속적으로");
		sixteen.add("체력이 약한 반복 행동의 양면성");
		sixteen.add("그 밖에 인지 능력과 관련된 설계");

		List<String> seventeen = new ArrayList<String>();
		seventeen.add("표면은 미끄럽지 않고, 모서리는 날카롭지 않게");
		seventeen.add("바닥은 안정성 있는 재질로");
		seventeen.add("눈부심 방지");

		List<String> eighteen = new ArrayList<String>();
		eighteen.add("위험 물질 파악");
		eighteen.add("접촉성 알레르기");
		eighteen.add("알레르기 유발 물질의 제거");

		List<String> nineteen = new ArrayList<String>();
		nineteen.add("목소리는 크고, 배경 소음은 작아야");
		nineteen.add("의사소통 시스템 이용");

		List<String> twenty = new ArrayList<String>();
		twenty.add("안전장치의 보장");
		List<String> twentione = new ArrayList<String>();
		twentione.add("숨쉬기 편하고 쾌적하게");
		List<String> twentitwo = new ArrayList<String>();
		twentitwo.add("물질의 화재 안전장치");

		listDataHashMap.put(listDataHeader.get(0), listChildData1); // Header, Child data
		listDataHashMap.put(listDataHeader.get(1), listChildData2);
		listDataHashMap.put(listDataHeader.get(2), three);
		listDataHashMap.put(listDataHeader.get(3), four);
		listDataHashMap.put(listDataHeader.get(4), five);
		listDataHashMap.put(listDataHeader.get(5), six);
		listDataHashMap.put(listDataHeader.get(6), seven);
		listDataHashMap.put(listDataHeader.get(7), eight);
		listDataHashMap.put(listDataHeader.get(8), nine);
		listDataHashMap.put(listDataHeader.get(9), ten);
		listDataHashMap.put(listDataHeader.get(10), eleven);
		listDataHashMap.put(listDataHeader.get(11), twelve);
		listDataHashMap.put(listDataHeader.get(12), thirteen);
		listDataHashMap.put(listDataHeader.get(13), fourteen);
		listDataHashMap.put(listDataHeader.get(14), fifteen);
		listDataHashMap.put(listDataHeader.get(15), sixteen);
		listDataHashMap.put(listDataHeader.get(16), seventeen);
		listDataHashMap.put(listDataHeader.get(17), eighteen);
		listDataHashMap.put(listDataHeader.get(18), nineteen);
		listDataHashMap.put(listDataHeader.get(19), twenty);
		listDataHashMap.put(listDataHeader.get(20), twentione);
		listDataHashMap.put(listDataHeader.get(21), twentitwo);

	}

	// 메뉴만들기
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater menuIn = getMenuInflater();
		menuIn.inflate(R.menu.sangse_first, menu);
		return true;

	}

	// 메뉴 event
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;

		}

		return false;
	}

}
