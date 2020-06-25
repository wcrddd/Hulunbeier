package cn.edu.upc.manage.dao;

import cn.edu.upc.manage.model.ContractStatistics;

public interface ContractStatisticsMapper {
    ContractStatistics getContractStatistics(int projectId);
}
