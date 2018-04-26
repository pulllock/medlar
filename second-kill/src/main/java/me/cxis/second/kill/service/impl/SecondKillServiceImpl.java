package me.cxis.second.kill.service.impl;

import me.cxis.second.kill.dao.SecondKillDao;
import me.cxis.second.kill.dto.ExposerDTO;
import me.cxis.second.kill.dto.SecondKillDTO;
import me.cxis.second.kill.dto.SecondKillExecution;
import me.cxis.second.kill.exception.RepeatKillException;
import me.cxis.second.kill.exception.SecondKillCloseException;
import me.cxis.second.kill.exception.SecondKillException;
import me.cxis.second.kill.service.SecondKillService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("secondKillService")
public class SecondKillServiceImpl implements SecondKillService {

    @Resource
    private SecondKillDao secondKillDao;

    @Override
    public List<SecondKillDTO> getSecondKillList() {
        return null;
    }

    @Override
    public SecondKillDTO getById(long secondKillId) {
        return null;
    }

    @Override
    public ExposerDTO exportSecondKillUrl(long secondKillId) {
        return null;
    }

    @Override
    public SecondKillExecution executeSecondKill(long secondKillId, long userPhone, String md5) throws SecondKillException, RepeatKillException, SecondKillCloseException {
        return null;
    }
}
