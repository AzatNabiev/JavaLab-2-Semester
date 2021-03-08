package ru.itis.javalab.repositories;

import org.springframework.stereotype.Repository;
import ru.itis.javalab.models.VideoInfo;

import java.util.List;

@Repository
public class VideoInfoRepositoryJdbcImpl implements VideoInfoRepository {
    @Override
    public void save(VideoInfo entity) {

    }

    @Override
    public void update(VideoInfo entity) {

    }

    @Override
    public void delete(VideoInfo entity) {

    }

    @Override
    public List<VideoInfo> findAll() {
        return null;
    }

    @Override
    public List<VideoInfo> findAll(int page, int size) {
        return null;
    }
}
