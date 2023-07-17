package com.example.demo.service;

import com.example.demo.utils.CommandLineTool;
import com.example.demo.utils.LogUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {

    @Scheduled(cron = "0 0/30 * * * ?")
    public void updateDoc() {
        System.out.println("=======updateDoc===start==" + System.currentTimeMillis());
        List<String> commands = new ArrayList<>();
        commands.add("./json/docUpdate.sh");
        List<String> strings = CommandLineTool.executeNewFlow(commands);
        LogUtils.logJson(strings);
        System.out.println("=======updateDoc===end==" + System.currentTimeMillis());
    }
}
