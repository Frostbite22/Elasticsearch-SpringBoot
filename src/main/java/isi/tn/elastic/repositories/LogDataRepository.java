package isi.tn.elastic.repositories;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import isi.tn.elastic.models.LogData;

public interface LogDataRepository extends ElasticsearchRepository<LogData, String> {
	List<LogData> findByHost(String host);
    List<LogData> findByMessageContaining(String message);

}
