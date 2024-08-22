package com.next1b.nextsvr.service.activity.buns;

import com.next1b.nextsvr.core.event.EventType;
import com.next1b.nextsvr.core.event.GameEvent;
import com.next1b.nextsvr.core.proto.PlayerHandler;
import com.next1b.nextsvr.proto.ActRanches;
import com.next1b.nextsvr.proto.ErrorCode;
import com.next1b.nextsvr.proto.PRTID;
import com.next1b.nextsvr.service.activity.ranches.RanchesService;
import com.next1b.nextsvr.service.activity.ranches.RanchesUtils;
import com.next1b.nextsvr.service.player.PlayerCharacter;
import com.next1b.nextsvr.util.net.proto.NetProtocol;
import com.next1b.nextsvr.util.single.SingletonManager;


/****************************************************************************
 * Copyright (c) 2019-2020 next-1b.com
 * <a href="http://next-1b.com">...</a>
 * @author: yg
 *****************************************************************************/
public class BunsHandler implements PlayerHandler {
    private RanchesService ranchesService;



    @Override
    public void init() {
        ranchesService = SingletonManager.getIns(RanchesService.class);
        this.regProto(PRTID.ENUM_PRTID.PRTID_RANCHES_GAME_INFO_REQ, BunsHandler::ranchesInfo);//请求游戏信息
        this.regProto(PRTID.ENUM_PRTID.PRTID_RANCHES_GAME_NEW_REQ, BunsHandler::newGame);//开始新游戏
        this.regProto(PRTID.ENUM_PRTID.PRTID_RANCHES_GAME_INIT_REQ, BunsHandler::initGame);//游戏初始化
        this.regProto(PRTID.ENUM_PRTID.PRTID_RANCHES_GAME_GRIDMOVE_REQ, BunsHandler::gridMove);//格子移动
        this.regProto(PRTID.ENUM_PRTID.PRTID_RANCHES_GAME_USEITEM_REQ, BunsHandler::useItem);//使用道具
        this.regProto(PRTID.ENUM_PRTID.PRTID_RANCHES_GAME_END_REQ, BunsHandler::endGame);//结束游戏

        this.regProto(PRTID.ENUM_PRTID.PRTID_RANCHES_TASK_INFO_REQ, BunsHandler::taskInfo);//任务详情
        this.regProto(PRTID.ENUM_PRTID.PRTID_RANCHES_REC_TASK_REQ, BunsHandler::taskReq);//领取任务奖励

        this.regProto(PRTID.ENUM_PRTID.PRTID_RANCHES_TOTAL_RANK_REQ, BunsHandler::totalScoreRank);//总积分排行榜


        this.regProto(PRTID.ENUM_PRTID.PRTID_RANCHES_DIAMOND_INFO_REQ, BunsHandler::diamondShopInfo);//钻石商店信息
        this.regProto(PRTID.ENUM_PRTID.PRTID_RANCHES_DIAMOND_EXCHANGE_REQ, BunsHandler::diamondExchange);//钻石商店购买
        this.regProto(PRTID.ENUM_PRTID.PRTID_RANCHES_SHOP_INFO_REQ, BunsHandler::payShopInfo);//充值商店信息
        this.regEvent(EventType.PAY, BunsHandler::onPay);

    }


    /**
     * 请求游戏信息
     *
     * @param protocol
     */
    private void ranchesInfo(NetProtocol protocol) {

        PlayerCharacter player = protocol.getOwner();
        if (!RanchesUtils.isOpen(player)) {
            player.sendErr(protocol.getProtoId(), ErrorCode.ErrCode.ACTIVITY_NOT_OPEN);
            return;
        }
        ranchesService.ranchesInfo(player);
    }

    /**
     * 开启游戏
     *
     * @param protocol
     */
    private void newGame(NetProtocol protocol) {

        PlayerCharacter player = protocol.getOwner();
        //检测活动是否开启
        if (!RanchesUtils.gameIsOpen(player)) {
            player.sendErr(protocol.getProtoId(), ErrorCode.ErrCode.ACTIVITY_NOT_OPEN);
            return;
        }

        int protoId = protocol.getProtoId();

        ranchesService.newGame(player, protoId);
    }

    /**
     * 初始化游戏
     *
     * @param protocol
     */
    private void initGame(NetProtocol protocol) {

        PlayerCharacter player = protocol.getOwner();
        ActRanches.PRT_RANCHES_GAME_INIT_REQ req = protocol.getProto();
        ActRanches.PRT_RANCHES_BOARD board = req.getBoard();

        //检测活动是否开启
        if (!RanchesUtils.gameIsOpen(player)) {
            player.sendErr(protocol.getProtoId(), ErrorCode.ErrCode.ACTIVITY_NOT_OPEN);
            return;
        }
        //初始化棋盘数据
        ranchesService.initGame(player, board, req.getUseRaws());
    }

    /**
     * 格子移动
     *
     * @param protocol
     */
    private void gridMove(NetProtocol protocol) {
        PlayerCharacter player = protocol.getOwner();
        //检测活动是否开启
        if (!RanchesUtils.gameIsOpen(player)) {
            player.sendErr(protocol.getProtoId(), ErrorCode.ErrCode.ACTIVITY_NOT_OPEN);
            return;
        }

        ActRanches.PRT_RANCHES_GAME_GRIDMOVE_REQ req = protocol.getProto();

        //移动
        ranchesService.gridMove(player, req);

    }

    /**
     * 使用道具
     *
     * @param protocol
     */
    private void useItem(NetProtocol protocol) {
        PlayerCharacter player = protocol.getOwner();
        //检测活动是否开启
        if (!RanchesUtils.gameIsOpen(player)) {
            player.sendErr(protocol.getProtoId(), ErrorCode.ErrCode.ACTIVITY_NOT_OPEN);
            return;
        }
        ActRanches.PRT_RANCHES_GAME_USEITEM_REQ req = protocol.getProto();

        //使用道具
        int protoId = protocol.getProtoId();
        ranchesService.useItem(player, req, protoId);

    }

    /**
     * 结束游戏
     *
     * @param protocol
     */
    private void endGame(NetProtocol protocol) {
        PlayerCharacter player = protocol.getOwner();

        //检测活动是否开启
        if (!RanchesUtils.gameIsOpen(player)) {
            player.sendErr(protocol.getProtoId(), ErrorCode.ErrCode.ACTIVITY_NOT_OPEN);
            return;
        }

        //结束游戏
        int protoId = protocol.getProtoId();
        ranchesService.endGame(player);

    }

    /**
     * 获取活跃数据信息
     *
     * @param protocol
     */
    private void taskInfo(NetProtocol protocol) {
        PlayerCharacter player = protocol.getOwner();
        //任务信息页
        ranchesService.taskInfo(player);

    }

    /**
     * 领取成就任务奖励
     *
     * @param protocol
     */
    private void taskReq(NetProtocol protocol) {
        PlayerCharacter player = protocol.getOwner();
        //检测活动是否开启
        if (!RanchesUtils.gameIsOpen(player)) {
            player.sendErr(protocol.getProtoId(), ErrorCode.ErrCode.ACTIVITY_NOT_OPEN);
            return;
        }
        ActRanches.PRT_RANCHES_REC_TASK_REQ req = protocol.getProto();

        //领取成就奖励
        int protoId = protocol.getProtoId();
        ranchesService.taskReq(player, req.getTaskDidsList(), protoId);

    }

    /**
     * 钻石商店信息
     *
     * @param protocol
     */
    private void diamondShopInfo(NetProtocol protocol) {
        PlayerCharacter player = protocol.getOwner();
        if (!RanchesUtils.gameIsOpen(player)) {
            player.sendErr(protocol.getProtoId(), ErrorCode.ErrCode.ACTIVITY_NOT_OPEN);
            return;
        }
        ranchesService.diamondShopInfo(player);
    }

    /**
     * 钻石兑换
     *
     * @param protocol
     */
    private void diamondExchange(NetProtocol protocol) {
        PlayerCharacter player = protocol.getOwner();
        if (!RanchesUtils.isOpen(player)) {
            player.sendErr(protocol.getProtoId(), ErrorCode.ErrCode.ACTIVITY_NOT_OPEN);
            return;
        }
        ActRanches.PRT_RANCHES_DIAMOND_EXCHANGE_REQ req = protocol.getProto();
        ranchesService.diamondExchange(player, req.getDid(), req.getNum());
    }

    /**
     * 真金商店信息
     *
     * @param protocol
     */
    private void payShopInfo(NetProtocol protocol) {
        PlayerCharacter player = protocol.getOwner();
        if (!RanchesUtils.gameIsOpen(player)) {
            player.sendErr(protocol.getProtoId(), ErrorCode.ErrCode.ACTIVITY_NOT_OPEN);
            return;
        }
        ranchesService.payShopInfo(player);
    }

    private void onPay(GameEvent event) {
        ranchesService.onPay(event);
    }

    /**
     * 获取排行榜信息
     *
     * @param protocol
     */
    private void totalScoreRank(NetProtocol protocol) {
        PlayerCharacter player = protocol.getOwner();
        if (!RanchesUtils.isOpen(player)) {
            player.sendErr(protocol.getProtoId(), ErrorCode.ErrCode.ACTIVITY_NOT_OPEN);
            return;
        }
        sendPlayerProtoToCenter(protocol);
    }
}
