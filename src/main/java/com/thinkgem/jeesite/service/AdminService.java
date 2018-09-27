package com.thinkgem.jeesite.service;

import java.util.List;
import java.util.Map;

public interface AdminService {

    List<Map<String, Object>> mysql(String sql);

    List<Map<String, Object>> oracle(String sql);
}
