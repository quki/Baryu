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
		setTitle("�����ħ��");
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

				// �Ѱ��� ���� �Լ�!! ���� position+1���ְ� 0�� �ٿ��� string������
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
		listDataHeader.add("��ü����Ȱ��");
		listDataHeader.add("������ ��ġ");
		listDataHeader.add("������ ����");
		listDataHeader.add("����� ���");
		listDataHeader.add("������ġ����");
		listDataHeader.add("��Ȯ�� ���");
		listDataHeader.add("�ɹ��� ��ȭ");
		listDataHeader.add("û�� ����");
		listDataHeader.add("����ó���ӵ�");
		listDataHeader.add("���³� ����");
		listDataHeader.add("��� ���̼�");
		listDataHeader.add("��ȿ�Ⱓǥ��");
		listDataHeader.add("���蹰�����");
		listDataHeader.add("ǥ�� �µ�");
		listDataHeader.add("���ٰ��ɰ��");
		listDataHeader.add("���� ����");
		listDataHeader.add("ǥ�� ó��");
		listDataHeader.add("������ ����");
		listDataHeader.add("���� ����");
		listDataHeader.add("������ġ");
		listDataHeader.add("ȯ��");
		listDataHeader.add("ȭ�������ġ");

		// Adding child data
		List<String> listChildData1 = new ArrayList<String>();
		listChildData1.add("��ü ������ �� ���� ���ٹ�");
		listChildData1.add("�ð� ������ �˰� ǥ�ÿ� û�� ��ȣ��");
		listChildData1.add("û�� ��ȣ�� ������δ� �ð� ������ �˰� ǥ�ø�");
		listChildData1.add("���� �Է��̳� ��ü �νĿ��� ��ü ���� Ȱ��");
		listChildData1.add("���ν� ȭ���� ������ ����");
		listChildData1.add("�ð� ��ȣ�� �Բ� ���� ������");

		List<String> listChildData2 = new ArrayList<String>();
		listChildData2.add("������ ������ġ�� ������ ���� ������ ����");
		listChildData2.add("�ð� ������ �ð� ������ �߽ɺο� ��ġ");
		listChildData2.add("������ġ�� ���� �� ��� �տ� ���� ��� ����");
		listChildData2.add("�ǹ��� ���� �� ������ġ�� ���ټ� ���");
		listChildData2.add("��ġ�Ӹ� �ƴ϶� ��ġ�� �߿�");

		List<String> three = new ArrayList<String>();
		three.add("������ �ʿ伺");
		three.add("���� ������ ����");
		three.add("���� �뵵�� �۾� ������ ���� ���� ����");
		three.add("���ν��� ������");

		List<String> four = new ArrayList<String>();
		four.add("������ ���ǿ� ���� �޶� ���δ�");
		four.add("���� ���Ǵ� ���� ��������");
		four.add("������ �ܵ� ����� ���� ��");
		four.add("�ֵ� ��� ���ü��� ���δ�");

		List<String> five = new ArrayList<String>();
		five.add("�۲��� ������ ũ�Ⱑ �ǵ��� ����");
		five.add("�ֵ��� ���� ����� �Ÿ������� �۲��� ũ��");
		five.add("����δ� �ѷ��ϰ�, ��ü�� �꼼����ü��");
		five.add("������ ȭ�鿡 ����ϴ� �۲�");

		List<String> six = new ArrayList<String>();
		six.add("��뼳���� �ؽ�Ʈ ���·�");
		six.add("������ ��Ȯ�ϰ� �ܼ��� ����");
		six.add("���� ��ǥ ���� ����");
		six.add("���� ���� �ÿ� �ʿ��� ���");
		six.add("�ٱ���� ���� ������ ���� �и�");
		six.add("���� �� ���� ã��� ��� ����");

		List<String> seven = new ArrayList<String>();
		seven.add("�ɹ��� ��ȭ�� �ǻ���뿡 ������ ����");

		List<String> eight = new ArrayList<String>();
		eight.add("û�� ������ �����ϱ� ������ ������ ������");

		List<String> nine = new ArrayList<String>();
		nine.add("���� ������ ������ �ӵ���");
		nine.add("������� ������ ����ؾ�");
		nine.add("�ð��� ���� ���ϴ� ������ ���� �� �ְ�");
		nine.add("�߰� �ð��� ��û�� �� �ְ�");

		List<String> ten = new ArrayList<String>();
		ten.add("���·� �ĺ��� �� �ְ�");
		ten.add("�˰� ǥ�÷� ���� ����");
		ten.add("���蹰���� �� ��⿡�� ���� ���");

		List<String> eleven = new ArrayList<String>();
		eleven.add("����ڸ� ����� ũ�⡤���� �� ����");
		eleven.add("��뼳���� ���� ����");
		eleven.add("������ġ�� ���� ������ ������ ������ �� �ְ�");
		eleven.add("����ϱ⿡ ����� �ð���");
		eleven.add("���� ������ ��ų� ���� ����");
		eleven.add("�ǹ��� �ü��� �����ϱ� ���� �۵��ϱ� ���ϰ�");

		List<String> twelve = new ArrayList<String>();
		twelve.add("��ǰ�Ӹ� �ƴ϶� �Ϲ� ��ǰ�� ��ȿ �Ⱓ�� �ִ�");

		List<String> thirteen = new ArrayList<String>();
		thirteen.add("���빰 �� Ư�� ���� ǥ��");
		thirteen.add("���� ���ǵ� ��Ȯ�ϰ�");

		List<String> fourteen = new ArrayList<String>();
		fourteen.add("����ڴ� �µ� �ڱؿ� �� �ΰ�");
		fourteen.add("������ �µ� ȯ��");

		List<String> fifteen = new ArrayList<String>();
		fifteen.add("������ �����̰� ������ �ʰ�");
		fifteen.add("������ ��ȭ�� ������ �°��⳪ ���� ��ġ");
		fifteen.add("���迡�� �ݵ�� �����̿� ���� ��ġ");
		fifteen.add("�ٴ��� �̲����� �ʰ�, ���� ã�� ���� ���ݱ� ���ϰ�");
		fifteen.add("��ü�� �������� �����ڼ��� �Բ� ����");
		fifteen.add("�ǹ��� ��� ������ ����");
		fifteen.add("��� ����� ����");

		List<String> sixteen = new ArrayList<String>();
		sixteen.add("������ ������ ������");
		sixteen.add("�ǵ�� ������ ����������");
		sixteen.add("ü���� ���� �ݺ� �ൿ�� ��鼺");
		sixteen.add("�� �ۿ� ���� �ɷ°� ���õ� ����");

		List<String> seventeen = new ArrayList<String>();
		seventeen.add("ǥ���� �̲����� �ʰ�, �𼭸��� ��ī���� �ʰ�");
		seventeen.add("�ٴ��� ������ �ִ� ������");
		seventeen.add("���ν� ����");

		List<String> eighteen = new ArrayList<String>();
		eighteen.add("���� ���� �ľ�");
		eighteen.add("���˼� �˷�����");
		eighteen.add("�˷����� ���� ������ ����");

		List<String> nineteen = new ArrayList<String>();
		nineteen.add("��Ҹ��� ũ��, ��� ������ �۾ƾ�");
		nineteen.add("�ǻ���� �ý��� �̿�");

		List<String> twenty = new ArrayList<String>();
		twenty.add("������ġ�� ����");
		List<String> twentione = new ArrayList<String>();
		twentione.add("������ ���ϰ� �����ϰ�");
		List<String> twentitwo = new ArrayList<String>();
		twentitwo.add("������ ȭ�� ������ġ");

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
