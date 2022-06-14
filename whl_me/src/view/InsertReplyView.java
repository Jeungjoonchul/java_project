package view;

import java.util.Scanner;

import dao.BookDAO;
import dao.Check;
import dao.ReplyDAO;
import dto.ReplyDTO;

public class InsertReplyView {
	public InsertReplyView() {
		ReplyDAO rpdao = new ReplyDAO();
		BookDAO bdao = new BookDAO();
		ReplyDTO newReply = new ReplyDTO();
		String[] inputInfo = { "리뷰 작성할 예약 번호 선택(", "점수 입력(0~10 / ", "내용 입력 (수정이 불가하니 신중하게 작성해주세요 / ", "" };
		for (int i = 0; i < inputInfo.length;) {
			Scanner sc = new Scanner(System.in);
			if (i == 3) {
				System.out.println("┏리뷰 미리보기\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println(newReply);
				System.out.println(
						"┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				System.out.print("■작성한 리뷰를 등록하시겠습니까?(Y/N) : ");
				sc = new Scanner(System.in);
				String checkInsert = sc.next();
				if (checkInsert.equalsIgnoreCase("Y")) {
					if (rpdao.insert(newReply)) {
						System.out.println("◎리뷰가 성공적으로 등록되었습니다.");
						break;
					} else {
						System.out.println("※리뷰 등록에 실패하였습니다.");
						break;
					}
				} else if (checkInsert.equalsIgnoreCase("N")) {
					System.out.println("※리뷰 작성을 취소합니다.");
					break;
				} else {
					System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
				}
			} else {
				System.out.print("■" + inputInfo[i] + "나가기는 '!') : ");
				sc = new Scanner(System.in);
				String inputData = sc.nextLine();
				if (inputData.equalsIgnoreCase("!")) {
					System.out.println("💤리뷰 작성하기를 종료합니다.");
					break;
				} else {
					switch (i) {
					case 0:
						// 리뷰 작성할 예약 번호 선택
						if (Check.validateNumber(inputData)) {
							if (bdao.select(Integer.parseInt(inputData)) != null) {
								newReply.book_num = bdao.select(Integer.parseInt(inputData)).book_num;
								newReply.restaurant_id = bdao.select(Integer.parseInt(inputData)).restaurant_id;
								newReply.user_id = bdao.select(Integer.parseInt(inputData)).user_id;
								i++;
							} else {
								System.out.println("※예약 번호를 찾을 수 없습니다. 확인 후 다시 시도해주세요!");
							}
						} else {
							System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
						}
						break;
					case 1:
						// 점수 입력
						if (Check.validateScore(inputData)) {
							newReply.reply_score = Integer.parseInt(inputData);
							i++;
						} else {
							System.out.println("※점수는 0~10점의 정수로 입력해주세요(ex : 5(가능) / 5.5(불가능))");
						}
						break;
					case 2:
						// 리뷰 내용 입력
						newReply.reply_comment = inputData;
						i++;
						break;
					}
				}
			}
		}
	}
}
