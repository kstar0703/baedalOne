package com.baedal.one.review.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import com.baedal.one.Main;
import com.baedal.one.review.dao.ReviewDao;
import com.baedal.one.review.service.ReviewService;
import com.baedal.one.review.vo.ReplyVo;
import com.baedal.one.review.vo.ReviewVo;
import com.baedal.one.review.vo.ReviewReplyVo;

public class ReviewController {

	ReviewService service;

	public ReviewController() {
		service = new ReviewService();
	}

	/**
	 * 리뷰 작성 
	 * INSERT INTO REVIEW (REVIEW_NO, STORE_NO, ORDER_NO, CONTENT, USER_NO)
	 * VALUES (SEQ_REVIEW.NEXTVAL,?,?,?,?)
	 * 
	 * @param storeNo
	 * @param userNo
	 * @param orederNo
	 */
	public void writeReview(String storeNo, String userNo, String orederNo) {

		try {
			// 데이터 입력받기
			ReviewVo vo = new ReviewVo();
			System.out.println("\n===== 리뷰 작성 =====");

			// 폄점남길 점수 입력
			System.out.print("\n평점을 1점부터 5점 사이로 입력하세요: ");
			String num = Main.SC.nextLine();

			int rating = Integer.parseInt(num);
			if (rating < 6 && rating > 0) {
				String strNum= String.valueOf(rating);
				vo.setRating(strNum); // 평점 입력
			} else {
				throw new Exception();
			}

			System.out.print("\n내용을 입력하세요: ");
			String content = Main.SC.nextLine();

			vo.setStoreNo(storeNo); // 매장번호 입력
			vo.setContent(content); // 리뷰내용 입력
			vo.setUserNo(userNo); // 회원번호 입력
			vo.setOrderNo(orederNo); // 주문번호 입력

			// 서비스 호출
			int result = service.writeReview(vo);

			// 결과집합
			if (result == 1) {
				System.out.println("\n리뷰 작성이 완료 되었습니다.");
			} else {
				throw new Exception();
			}

			// 예외처리
		} catch (java.sql.SQLIntegrityConstraintViolationException e) {
			System.err.println("\n이미 리뷰를 작성했습니다.");
		} catch (java.util.InputMismatchException e) {
			System.err.println("\n번호를 입력해주세요.");
		} catch (java.lang.NumberFormatException e) {
			System.err.println("\n숫자를 입력해주세요.");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("디비 연결 실패...");
		} catch (Exception e) {
			System.err.println("\n1~5점 사이로 입력하세요.");
		}

	}

	/**
	 * 매장 모든 리뷰 조회하기 
	 * SELECT NICKNAME , TO_CHAR(WRITE_DATE,'YYYY-MM-DD hh24:mi') AS
	 * WRITE_DATE , CONTENT,MENU_NAME , R.ORDER_NO , R.STORE_NO , R.REVIEW_NO FROM
	 * REVIEW R JOIN ORDERS O ON R.ORDER_NO = O.ORDER_NO JOIN MEMBER MB ON O.USER_NO
	 * = MB.MEMBER_NO RIGHT JOIN CART_LIST C ON O.CART_NO = C.CART_NO JOIN MENU M ON
	 * C.MENU_NO = M.MENU_NO WHERE R.STORE_NO = ? AND R.DELETE_YN = 'N' ORDER BY
	 * R.REVIEW_NO DESC
	 * 
	 * @param storeNo
	 */
	public void storeReview(String storeNo) {
		
			System.out.println("\n--------------리뷰 조회---------------");

			ReviewVo vo = new ReviewVo();

			// vo객체에 매장번호 입력
			vo.setStoreNo(storeNo);
			System.out.println();

			// 리플라이 vo가져오기
//			List<ReplyVo> replyVoList = checkReply(storeNo);
			
			Map<String, String> menuNameMap = new HashMap<>();
			
			try {
				// 서비스 호출하기
				ArrayList<ReviewReplyVo> reRpVoList = service.storeReview(vo);
				
				List<ReplyVo> replyVoList = new ArrayList<ReplyVo>();
				List<ReviewVo> reviewVoList = new ArrayList<ReviewVo>();
				
				//ReviewVo 타입의 리스트 생성후 reRpVoList에 ReviewVo를 입력
				for(ReviewReplyVo r  : reRpVoList) {
					reviewVoList.add(r.getReviewVo());
				}
				
				for(ReviewVo r : reviewVoList) {
					menuNameMap.put(r.getOrderNo(), menuNameMap.getOrDefault(r.getOrderNo(), "")+", "+r.getMenuName());
				}
				
				List<ReviewVo> newReviewVoList = reviewVoList.stream().distinct().collect(Collectors.toList());
				
				int x = 0;
				for(ReviewVo r : newReviewVoList) {
					System.out.println(r);
					System.out.println(menuNameMap.get(r.getOrderNo()));
					
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
	}

			
			

	/**
	 * 회원 모든 리뷰 조회하기 
	 * SELECT NICKNAME , TO_CHAR(WRITE_DATE,'YYYY-MM-DD hh24:mi') AS
	 * WRITE_DATE , CONTENT ,MENU_NAME , R.ORDER_NO , R.STORE_NO , R.REVIEW_NO FROM
	 * REVIEW R JOIN ORDERS O ON R.ORDER_NO = O.ORDER_NO JOIN MEMBER MB ON O.USER_NO
	 * = MB.MEMBER_NO RIGHT JOIN CART_LIST C ON O.CART_NO = C.CART_NO JOIN MENU M ON
	 * C.MENU_NO = M.MENU_NO WHERE O.USER_NO = ? AND R.DELETE_YN = 'N' ORDER BY
	 * R.REVIEW_N
	 * 
	 * @param userNo
	 */
	public void userReview(String userNo) {
		try {
			System.out.println("===== 리뷰조회 =====");
			System.out.println();

			// 서비스 호출하기
			ArrayList<ReviewVo> dbVo = service.userReview(userNo);

			// 리스트에서 첫번째 주문번호 가져오기
			String orderNo = dbVo.get(0).getOrderNo();

			// 리스트에서 첫번째 리뷰번호 가져오기
			String reviewNo = dbVo.get(0).getReviewNo();

			// map 생성
			HashMap<Integer, String> map = new HashMap<Integer, String>();
			int i = 1;
			for (ReviewVo voList : dbVo) {

				// 주문번호가 같을때만 출력
				if (voList.getOrderNo().equals(orderNo)) {
					// map의 key값에 i를 넣고 value 값을 주문번호로 설정
					map.put(i, voList.getOrderNo());
					// 콘솔에 리뷰 츌력
					System.out.println(i + ".꒰⑅•ᴗ•⑅꒱ " + voList.getNickName());
					System.out.println(voList.getWriteDate());
					// 평점 별모양으로 출력
					System.out.print("평점: ");
					switch (voList.getRating()) {
					case 1:
						System.out.println("☆☆☆☆★");
						break;
					case 2:
						System.out.println("☆☆☆★★");
						break;
					case 3:
						System.out.println("☆☆★★★");
						break;
					case 4:
						System.out.println("☆★★★★");
						break;
					case 5:
						System.out.println("★★★★★");
						break;
					}
					System.out.println();
					System.out.println(voList.getContent());
					System.out.println();
					System.out.println("< 주문메뉴 >");
					System.out.print(voList.getMenuName());
					orderNo = "";
					i++;

				} else if (!voList.getReviewNo().equals(reviewNo)) {

					map.put(i, voList.getOrderNo());

					System.out.println();
					System.out.println("\n---------------------------------");
					System.out.println();
					System.out.println(i + ".꒰⑅•ᴗ•⑅꒱ " + voList.getNickName());
					System.out.println(voList.getWriteDate());
					System.out.print("평점: ");
					switch (voList.getRating()) {
					case 1:
						System.out.println("☆☆☆☆★");
						break;
					case 2:
						System.out.println("☆☆☆★★");
						break;
					case 3:
						System.out.println("☆☆★★★");
						break;
					case 4:
						System.out.println("☆★★★★");
						break;
					case 5:
						System.out.println("★★★★★");
						break;
					}
					System.out.println();
					System.out.println(voList.getContent());
					System.out.println();
					System.out.println("< 주문메뉴 >");
					System.out.print(voList.getMenuName());

					// 변한 리뷰번호를 변수에 새로 할당
					reviewNo = voList.getReviewNo();
					// 리뷰 한개당 i 1씩 증가
					i++;

					// 주문번호가 다를때 출력
				} else {

					// 메뉴출력
					System.out.print("," + voList.getMenuName());

				}

			}
			// 삭제 할것인지 물어보기
			System.out.println();
			System.out.println("\n---------------------------------");
			System.out.println("1. 리뷰삭제 / 2. 리뷰수정 / 3.뒤로가기");
			System.out.print("\n번호를 입력하세요: ");
			String num = Main.SC.nextLine();
			switch (num) {
			case "1":
				DeleteReview(map, userNo);
				break;
			case "2":
				updateReview(map, userNo);
				break;
			case "3":
				break;
			default:
				System.out.println("다시 입력하세요.");
				break;
			}
		} catch (java.lang.IndexOutOfBoundsException e) {
			System.err.println("\n작성된 리뷰가 없습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("리뷰 조회 실패...");
		}
	}

	/**
	 * 리뷰 수정
	 * 
	 * @param map
	 * @param userNo
	 */
	public void updateReview(HashMap<Integer, String> map, String userNo) {

		try {
			// 데이터 입력받기
			System.out.println("\n===== 리뷰수정 =====");
			System.out.print("\n수정할 리뷰의 번호를 입력하세요: ");
			int num = Main.SC.nextInt();
			System.out.print("\n\n수정할 내용을 입력하세요: ");
			Main.SC.nextLine();
			String content = Main.SC.nextLine();

			// 생성자에서 입력받은 주문번호 변수에 할당
			String orderNo = map.get(num);
			// vo객체에 매개변수의 값들 입력
			ReviewVo vo = new ReviewVo();
			vo.setOrderNo(orderNo);
			vo.setUserNo(userNo);
			vo.setContent(content);

			// 서비스 호출
			int result = service.updateReview(vo);

			// result가 1이 안나오면 예외발생
			if (result != 1) {
				throw new Exception();
			}

			// 리뷰 수정 완료
			System.out.println("\n리뷰 수정이 완료 되었습니다.");

			// 예외처리
		} catch (java.util.InputMismatchException e) {
			System.err.println("\n번호를 입력하세요.");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("\n리뷰 데이터 불러오기 실패");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("\n존재하지 않는 리뷰 번호 입니다.");
		}

	}

	/**
	 * 리뷰 삭제
	 * 
	 * UPDATE REVIEW SET DELETE_YN = 'Y' WHERE REVIEW_NO = ?
	 * 
	 * @param map
	 * @param userNo
	 */
	public void DeleteReview(HashMap<Integer, String> map, String userNo) {

		try {
			// 데이터 입력받기
			System.out.println("\n===== 리뷰삭제 =====");
			System.out.print("\n삭제할 리뷰에 번호를 입력해주세요: ");
			int reviewNo = Main.SC.nextInt();

			// vo 객체에 입력받은 값 입력
			ReviewVo vo = new ReviewVo();
			vo.setReviewNo(map.get(reviewNo));
			vo.setUserNo(userNo);

			// service 호출
			int result = service.deleteReview(vo);

			// 결과처리
			if (result != 1) {
				throw new Exception();
			}
			System.out.println("\n리뷰 삭제완료!");

			// 예외처리
		} catch (java.util.InputMismatchException e) {
			System.err.println("\n번호를 입력하세요.");
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("\n리뷰 데이터 불러오기 실패");
		} catch (Exception e) {
			System.err.println("\n존재하지 않는 리뷰 번호 입니다.");
		}
	}

	/**
	 * 답변 작성 
	 * INSERT INTO REPLY (REPLY_NO,REVIEW_NO,CONTENT) VALUES
	 * (SEQ_REPLY.NEXTVAL,?,?)
	 * 
	 * @param reviewNo
	 */
	public void WriteReply(String reviewNo) {

		System.out.println("\n1. 답변작성");
		System.out.println("2. 뒤로가기");
		System.out.print("번호를 입력하세요: ");
		String num = Main.SC.nextLine();

		switch (num) {
		case "1":
			break;
		case "2":
			return;
		default:
			System.out.println("\n잘못된 입력입니다.");
			return;
		}

		try {
			// 리뷰번호 vo에 입력
			ReplyVo vo = new ReplyVo();
			vo.setReviewNo(reviewNo);

			// 스캐너로 답변내용 입력받기
			System.out.print("\n답변 내용을 입력하세요: ");
			String content = Main.SC.nextLine();

			// 사장님 답변내용 vo에 입력
			vo.setContent(content);

			// 서비스 호출
			int result = service.writeReply(vo);

			// 결과집합
			if (result != 1) {
				throw new Exception();
			}
			System.out.println("\n답변 작성 완료!");
		} catch (java.sql.SQLIntegrityConstraintViolationException e) {
			System.err.println("\n이미 작성된 답변입니다.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("\n답변 작성중 디비 연결 실패..");
		}

	}

	/**
	 * 답변조회
	 * SELECT RE.CONTENT, RE.REPLY_NO, RE.REVIEW_NO
	 * FROM REPLY RE
	 * JOIN REVIEW R ON RE.REVIEW_NO = R.REVIEW_NO
	 * WHERE R.STORE_NO = 1;
	 * @param storeNo
	 * @return
	 */
	public List<ReplyVo> checkReply(String storeNo) {
		List<ReplyVo> voList = null;
		try {
			voList = service.checkReply(storeNo);
			
			if(voList.get(0) == null) {
				throw new Exception();
			}
			System.out.println(voList.get(0).getContent());
			return voList;
		}catch (SQLException e) {
			System.out.println("");
		}catch (Exception e) {
			System.out.println("");
		}
		return voList;
		
	}

}//class
