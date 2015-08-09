package com.quki.gongmo.bary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

public class GyuoFirst extends AppCompatActivity {

	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader; // parent�̸�(������ 1��1����)
	HashMap<String, List<String>> listDataChild;
	private int lastExpandedPosition = -1;

	int p, c;
	String a, b, myIds;

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
		setContentView(R.layout.gyuo_first);
		setTitle("�����Ʒñ��");
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		// get the listview
		expListView = (ExpandableListView) findViewById(R.id.lvExp);

		// preparing list data
		prepareListData();

		listAdapter = new ExpandableListAdapter(this, listDataHeader,
				listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);
		expListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {

				// �Ѱ��� ���� �Լ�!! ���� position+1���ְ� 0�� �ٿ��� string������
				p = groupPosition + 1;
				c = childPosition + 1;

				try {
					if (p == 1) {
						String[] seoul = { "&s_page=311&s_list=7",
								"&s_page=318&s_list=11",
								"&s_page=329&s_list=24",
								"&s_page=353&s_list=9",
								"&s_page=362&s_list=16",
								"&s_page=378&s_list=20",
								"&s_page=398&s_list=7", "&s_page=405&s_list=2",
								"&s_page=407&s_list=26" };
						myIds = seoul[10 * p + c - 11];
					} else if (p == 2) {
						String[] gyungi = { "&s_page=49&s_list=16",
								"&s_page=297&s_list=14",
								"&s_page=433&s_list=22",
								"&s_page=455&s_list=24",
								"&s_page=486&s_list=15",
								"&s_page=501&s_list=14",
								"&s_page=602&s_list=30",
								"&s_page=778&s_list=11" };
						myIds = gyungi[10 * p + c - 21];
					} else if (p == 3) {
						String[] gyunsang = { "&s_page=11&s_list=11",
								"&s_page=100&s_list=15",
								"&s_page=479&s_list=7",
								"&s_page=515&s_list=33",
								"&s_page=556&s_list=6",
								"&s_page=675&s_list=12",
								"&s_page=687&s_list=25",
								"&s_page=773&s_list=5", "&s_page=789&s_list=10" };
						myIds = gyunsang[10 * p + c - 31];

					} else if (p == 4) {
						String[] junra = { "&s_page=115&s_list=10",
								"&s_page=207&s_list=8", "&s_page=548&s_list=8",
								"&s_page=632&s_list=5", "&s_page=654&s_list=16" };
						myIds = junra[10 * p + c - 41];
					} else if (p == 5) {
						String[] chungchung = { "&s_page=215&s_list=9",
								"&s_page=712&s_list=17",
								"&s_page=729&s_list=21",
								"&s_page=757&s_list=12" };
						myIds = chungchung[10 * p + c - 51];
					} else if (p == 6) {
						String[] gangwon = { "&s_page=0&s_list=11",
								"&s_page=586&s_list=9", "&s_page=750&s_list=7",
								"&s_page=769&s_list=4" };
						myIds = gangwon[10 * p + c - 61];
					} else if (p == 7) {
						String[] gangwon = { "&s_page=670&s_list=5" };
						myIds = gangwon[10 * p + c - 71];
					} else if (p == 8) {
						String[] busan = { "&s_page=224&s_list=17",
								"&s_page=241&s_list=16",
								"&s_page=257&s_list=22",
								"&s_page=279&s_list=18" };
						myIds = busan[10 * p + c - 81];
					} else if (p == 9) {
						String[] daegu = { "&s_page=125&s_list=20",
								"&s_page=145&s_list=13",
								"&s_page=158&s_list=26" };
						myIds = daegu[10 * p + c - 91];
					} else if (p == 10) {
						String[] incheon = { "&s_page=22&s_list=27",
								"&s_page=637&s_list=17" };
						myIds = incheon[10 * p + c - 101];
					} else if (p == 11) {
						String[] daejeon = { "&s_page=184&s_list=23",
								"&s_page=595&s_list=7" };
						myIds = daejeon[10 * p + c - 111];
					} else if (p == 12) {
						String[] kwanju = { "&s_page=70&s_list=6",
								"&s_page=76&s_list=24" };
						myIds = kwanju[10 * p + c - 121];
					} else if (p == 13) {
						String[] ulsan = { "&s_page=562&s_list=24" };
						myIds = ulsan[10 * p + c - 131];
					}
				} catch (Exception e) {
				}

				// intent
				Intent myIntent = new Intent(getApplicationContext(),
						GyuoSecond.class);
				myIntent.putExtra("myIds", myIds);
				myIntent.putExtra("parent", listDataHeader.get(groupPosition));
				myIntent.putExtra("child",
						listDataChild.get(listDataHeader.get(groupPosition))
								.get(childPosition));
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
	}

	/*
	 * Preparing the list data
	 */
	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		// Adding parent data
		listDataHeader.add("����");
		listDataHeader.add("���");
		listDataHeader.add("���");
		listDataHeader.add("����");
		listDataHeader.add("��û");
		listDataHeader.add("����");
		listDataHeader.add("����");
		listDataHeader.add("�λ�");
		listDataHeader.add("�뱸");
		listDataHeader.add("��õ");
		listDataHeader.add("����");
		listDataHeader.add("����");
		listDataHeader.add("���");

		// Adding child data
		List<String> one = new ArrayList<String>();
		one.add("��������");
		one.add("��������");
		one.add("��������");
		one.add("��������");
		one.add("�Ϻ�����");
		one.add("��������");
		one.add("��������");
		one.add("��������");
		one.add("��������");

		List<String> two = new ArrayList<String>();
		two.add("�������");
		two.add("��õ����");
		two.add("��������");
		two.add("��������");
		two.add("�Ȼ�����");
		two.add("�Ⱦ�����");
		two.add("����������");
		two.add("��������");

		// ���
		List<String> three = new ArrayList<String>();
		three.add("�������");
		three.add("��������");
		three.add("�ȵ�����");
		three.add("�������");
		three.add("��������");
		three.add("��������");
		three.add("â������");
		three.add("�뿵����");
		three.add("��������");

		// ����
		List<String> four = new ArrayList<String>();
		four.add("��������");
		four.add("��������");
		four.add("��������");
		four.add("�ͻ�����");
		four.add("��������");

		// ��û
		List<String> five = new ArrayList<String>();
		five.add("��������");
		five.add("õ������");
		five.add("û������");
		five.add("��������");

		// ����
		List<String> six = new ArrayList<String>();
		six.add("��������");
		six.add("��������");
		six.add("��õ����");
		six.add("�¹�����");

		// ����
		List<String> seven = new ArrayList<String>();
		seven.add("��������");

		// �λ�
		List<String> eight = new ArrayList<String>();
		eight.add("��������");
		eight.add("�Ϻ�����");
		eight.add("�ߺ�����");
		eight.add("��������");

		// �뱸
		List<String> nine = new ArrayList<String>();
		nine.add("�Ϻ�����");
		nine.add("��������");
		nine.add("��������");

		// ��õ
		List<String> ten = new ArrayList<String>();
		ten.add("������������");
		ten.add("�Ϻ�����");

		// ����
		List<String> eleven = new ArrayList<String>();
		eleven.add("��������");
		eleven.add("��������");

		// ����
		List<String> twelve = new ArrayList<String>();
		twelve.add("��������");
		twelve.add("��������");

		// ���
		List<String> thirteen = new ArrayList<String>();
		thirteen.add("��������");

		listDataChild.put(listDataHeader.get(0), one);
		listDataChild.put(listDataHeader.get(1), two);
		listDataChild.put(listDataHeader.get(2), three);
		listDataChild.put(listDataHeader.get(3), four);
		listDataChild.put(listDataHeader.get(4), five);
		listDataChild.put(listDataHeader.get(5), six);
		listDataChild.put(listDataHeader.get(6), seven);
		listDataChild.put(listDataHeader.get(7), eight);
		listDataChild.put(listDataHeader.get(8), nine);
		listDataChild.put(listDataHeader.get(9), ten);
		listDataChild.put(listDataHeader.get(10), eleven);
		listDataChild.put(listDataHeader.get(11), twelve);
		listDataChild.put(listDataHeader.get(12), thirteen);

	}

	// �޴������
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater menuIn = getMenuInflater();
		menuIn.inflate(R.menu.sangse_first, menu);
		return true;

	}

	// �޴� event
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
