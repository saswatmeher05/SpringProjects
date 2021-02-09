package in.nareshit.somu.service;

import java.util.List;
import java.util.Optional;

import in.nareshit.somu.model.OrderMethod;

public interface IOrderMethodService {
	Integer saveOrderMethod(OrderMethod om);
	List<OrderMethod> getAllOrderMethods();
	boolean isOrderMethodExist(Integer id);
	void deleteOrderMethod(Integer id);
	Optional<OrderMethod> getOneOrderMethod(Integer id);
	void updateOrderMethod(OrderMethod om);
	boolean isOrderMethodExistByCode(String orderCode);
}
