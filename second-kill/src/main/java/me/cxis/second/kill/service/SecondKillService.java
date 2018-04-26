package me.cxis.second.kill.service;

import me.cxis.second.kill.dto.ExposerDTO;
import me.cxis.second.kill.dto.SecondKillDTO;
import me.cxis.second.kill.dto.SecondKillExecution;
import me.cxis.second.kill.exception.RepeatKillException;
import me.cxis.second.kill.exception.SecondKillCloseException;
import me.cxis.second.kill.exception.SecondKillException;

import java.util.List;

public interface SecondKillService {

    List<SecondKillDTO> getSecondKillList();

    SecondKillDTO getById(long secondKillId);

    ExposerDTO exportSecondKillUrl(long secondKillId);

    SecondKillExecution executeSecondKill(long secondKillId, long userPhone, String md5)
            throws SecondKillException, RepeatKillException, SecondKillCloseException;
}
