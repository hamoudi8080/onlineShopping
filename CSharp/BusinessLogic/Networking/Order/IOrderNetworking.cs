using ModelClasses;

namespace BusinessLogicServer.Networking.Order;

public interface IOrderNetworking
{
    public Task<List<OrdersDTO>> GetAllOrdersAsync();
    public Task<UserDTO> GetCustomer(string orderUsername);
    public Task<List<OrdersDTO>> GetAllOrdersByStatusAsync(string status);

    public Task<List<OrderLineDTO>> GetOrderLines(long id);

    public Task UpdateOrderStatus(long id, string status);

    public Task<List<OrdersDTO>> GetAllOrdersByUsername(string username);

}