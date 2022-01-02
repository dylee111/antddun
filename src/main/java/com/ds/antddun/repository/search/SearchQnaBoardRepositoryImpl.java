package com.ds.antddun.repository.search;

import com.ds.antddun.entity.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;
import java.util.stream.Collectors;


@Log4j2
public class SearchQnaBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchQnaBoardRepository {

    public SearchQnaBoardRepositoryImpl(){
        super(QnaBoard.class);
    }

    @Override
    public QnaBoard search1() {
        log.info("search1...");
        QQnaBoard qQnaBoard = QQnaBoard.qnaBoard;
        QQnaReply qQnaReply = QQnaReply.qnaReply;
        QQnaLikes qQnaLikes = QQnaLikes.qnaLikes;

        JPQLQuery<QnaBoard> jpqlQuery = from(qQnaBoard);
        jpqlQuery.leftJoin(qQnaReply).on(qQnaReply.qnaBoard.qnaNo.eq(qQnaBoard.qnaNo));
        jpqlQuery.leftJoin(qQnaLikes).on(qQnaLikes.qnaBoard.qnaNo.eq(qQnaBoard.qnaNo));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(qQnaBoard, qQnaReply.countDistinct(), qQnaLikes.countDistinct());
        tuple.groupBy(qQnaBoard.qnaNo);

        log.info("--------------");
        log.info(tuple);
        log.info("--------------");

        List<Tuple> result = tuple.fetch();

        log.info(result);

        return null;
    }


    @Override
    public Page<Object[]> searchPage(int cate, String type, String keyword, Pageable pageable) {
        log.info("searchPage...");

        QQnaBoard qQnaBoard = QQnaBoard.qnaBoard;
        QQnaReply qQnaReply = QQnaReply.qnaReply;
        QQnaLikes qQnaLikes = QQnaLikes.qnaLikes;

        JPQLQuery<QnaBoard> jpqlQuery = from(qQnaBoard);
        jpqlQuery.leftJoin(qQnaReply).on(qQnaReply.qnaBoard.qnaNo.eq(qQnaBoard.qnaNo));
        jpqlQuery.leftJoin(qQnaLikes).on(qQnaLikes.qnaBoard.qnaNo.eq(qQnaBoard.qnaNo));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(qQnaBoard, qQnaLikes.countDistinct(), qQnaReply.countDistinct());

        BooleanBuilder booleanBuilder = new BooleanBuilder(); //검색을 실행하는 객체
        BooleanExpression expression = qQnaBoard.qnaNo.gt(0L); //검색의 조건을 처리하는 객체

        booleanBuilder.and(expression);

        if(cate != 0){
            booleanBuilder.and(qQnaBoard.jobList.jno.eq(cate));
        }

        if(type != null){
            String[] typeArr = type.split("");
            //검색 조건 작성
            BooleanBuilder conditionBuilder = new BooleanBuilder();

            for (String t:typeArr){
                switch (t) {
                    case "t": conditionBuilder.or(qQnaBoard.title.contains(keyword));
                        break;
                    case "c": conditionBuilder.or(qQnaBoard.content.contains(keyword));
                        break;
                }
            }
            booleanBuilder.and(conditionBuilder);
        }

        tuple.where(booleanBuilder);
        //order by
        Sort sort = pageable.getSort();
        sort.stream().forEach(order ->{
            Order direction = order.isAscending()?Order.ASC: Order.DESC;
            String prop = order.getProperty();
            PathBuilder orderByExpression = new PathBuilder(QnaBoard.class, "qnaBoard");
            tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
        });
        tuple.groupBy(qQnaBoard.qnaNo);
        //page 처리
        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch();
        log.info(result);

        Long count = tuple.fetchCount();
        log.info("COUNT: "+count);

        return new PageImpl<Object[]>(result.stream().map(t -> t.toArray()).collect(Collectors.toList()),
                                      pageable,
                                      count);

    }
}

