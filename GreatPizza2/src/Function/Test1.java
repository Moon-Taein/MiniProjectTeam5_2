package Function;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		// 문태인 바보 ㅠㅠ

		// 일단 포스기 쪽에서

		// 키오스크 프레임

		// 주문을 어떤 형식으로 받고 어떤 형식으로 전달할지
		// 일일현황, 정산결과 등을 어떻게 보관할것인지
		// 파일을 사용해서 보관할것과 DB에 그냥 보관할것 나누기

		// 주문이 있는지 계속해서 확인하는 동작 필요
		// 주문 발생시 사운드와 함께 주문내역에 주문내용 읽어서 표시해주기
		// 주문 상태 색깔로 표시

		// 피자를 만들고 포장해서 고객에게 전달 하는 일련의 과정
		// 데이터 후처리

		// 주문부터 고객전달까지 일련의 과정에서 필요한 동작
		// 위 동작이 완료 되었을때 데이터 처리

		// mainorder count 필요하고 초기화 시키는 시점 필요할듯
		// 총가격을 넣어줘야되서 gui상에서 계산된 그 값을 주문완료 시점에 넣어줘야된다 생성할때는 0을 가지고 있게 만들고
		// 프레임 밑의 가격 계산되는곳의 걸로 계속 갱신하던가 마지막에 넣던가
//		MainOrder mo = new MainOrder(1, 97500);

		// detailorder 또한 동일
		// 버튼을 눌러서 피자메뉴의 id와 inventory안의 사이드, 음료 id를 가져와서 생성해야된다
//		DetailOrder deo = new DetailOrder(4, "음료_콜라500", 1, mo.getOrderNumber());

		// gui 상에서 읽어온 데이터를 기반으로 위와 아래의 string 값을 넣어줘야됨
		// 버튼을 눌러서 inventory의
//		MenuItem mi = new MenuItem(deo.getDetailOrderNumber(), "토핑_초콜릿");
//
//		Sql_Methods sqm = new Sql_Methods();

		// mainorder insert
//		sqm.mainOrderInsert(mo);

		// detailorder insert
//		sqm.detailOrderInsert(deo);

		// menuitem insert
//		sqm.menuitemInsert(mi);
	}
}