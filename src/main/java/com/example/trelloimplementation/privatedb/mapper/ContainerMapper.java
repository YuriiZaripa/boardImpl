package com.example.trelloimplementation.privatedb.mapper;

import com.example.trelloimplementation.privatedb.entity.Container;
import com.example.trelloimplementation.privatedb.vo.ContainerVO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContainerMapper {

    Container toContainer(ContainerVO containerVO);

    ContainerVO toContainerVO(Container container);

    List<ContainerVO> toListVO(List<Container> containers);

    List<Container> toContainerList(List<ContainerVO> containerVOs);

}
