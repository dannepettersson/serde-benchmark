package se.vermiculus.benchmark.serialization.protobuf;

import se.vermiculus.benchmark.serialization.model.proto.Address;
import se.vermiculus.benchmark.serialization.model.proto.Category;
import se.vermiculus.benchmark.serialization.model.proto.Customer;
import se.vermiculus.benchmark.serialization.model.proto.Order;
import se.vermiculus.benchmark.serialization.model.proto.OrderLine;
import se.vermiculus.benchmark.serialization.model.proto.OrderStatus;
import se.vermiculus.benchmark.serialization.model.proto.Product;

import java.util.stream.Collectors;

public class ProtobufMapper {

    public Order map(se.vermiculus.benchmark.messages.Order order) {
        if (order == null) {
            return null;
        }
        Order.Builder builder = Order.newBuilder();
        if (order.id() != null) {
            builder.setId(order.id());
        }
        if (order.customer() != null) {
            builder.setCustomer(map(order.customer()));
        }
        if (order.lines() != null) {
            builder.addAllLines(order.lines().stream().map(this::map).collect(Collectors.toList()));
        }
        if (order.status() != null) {
            builder.setStatus(OrderStatus.valueOf(order.status().name()));
        }
        if (order.created() != null) {
            builder.setCreated((int) order.created().toEpochDay());
        }
        if (order.shippingAddress() != null) {
            builder.setShippingAddress(map(order.shippingAddress()));
        }
        if (order.notes() != null) {
            builder.setNotes(order.notes());
        }
        return builder.build();
    }

    public Customer map(se.vermiculus.benchmark.messages.Customer customer) {
        if (customer == null) {
            return null;
        }
        Customer.Builder builder = Customer.newBuilder();
        builder.setId(customer.id());
        if (customer.firstName() != null) {
            builder.setFirstName(customer.firstName());
        }
        if (customer.lastName() != null) {
            builder.setLastName(customer.lastName());
        }
        if (customer.email() != null) {
            builder.setEmail(customer.email());
        }
        if (customer.dateOfBirth() != null) {
            builder.setDateOfBirth((int) customer.dateOfBirth().toEpochDay());
        }
        builder.setVip(customer.vip());
        if (customer.addresses() != null) {
            builder.addAllAddresses(customer.addresses().stream().map(this::map).collect(Collectors.toList()));
        }
        return builder.build();
    }

    public Address map(se.vermiculus.benchmark.messages.Address address) {
        if (address == null) {
            return null;
        }
        Address.Builder builder = Address.newBuilder();
        if (address.street() != null) {
            builder.setStreet(address.street());
        }
        if (address.city() != null) {
            builder.setCity(address.city());
        }
        if (address.zip() != null) {
            builder.setZip(address.zip());
        }
        if (address.country() != null) {
            builder.setCountry(address.country());
        }
        return builder.build();
    }

    public OrderLine map(se.vermiculus.benchmark.messages.OrderLine line) {
        if (line == null) {
            return null;
        }
        OrderLine.Builder builder = OrderLine.newBuilder();
        if (line.product() != null) {
            builder.setProduct(map(line.product()));
        }
        if (line.quantity() != null) {
            builder.setQuantity(line.quantity().intValueExact());
        }
        if (line.unitPrice() != null) {
            builder.setUnitPrice(line.unitPrice().toString());
        }
        builder.setDiscount(line.discount());
        return builder.build();
    }

    public Product map(se.vermiculus.benchmark.messages.Product product) {
        if (product == null) {
            return null;
        }
        Product.Builder builder = Product.newBuilder();
        if (product.sku() != null) {
            builder.setSku(product.sku());
        }
        if (product.name() != null) {
            builder.setName(product.name());
        }
        if (product.description() != null) {
            builder.setDescription(product.description());
        }
        if (product.category() != null) {
            builder.setCategory(map(product.category()));
        }
        if (product.price() != null) {
            builder.setPrice(product.price().toString());
        }
        builder.setWeightGrams(product.weightGrams());
        if (product.tags() != null) {
            builder.addAllTags(product.tags());
        }
        return builder.build();
    }

    public Category map(se.vermiculus.benchmark.messages.Category category) {
        if (category == null) {
            return null;
        }
        Category.Builder builder = Category.newBuilder();
        builder.setId(category.id());
        if (category.name() != null) {
            builder.setName(category.name());
        }
        if (category.parent() != null) {
            builder.setParent(map(category.parent()));
        }
        return builder.build();
    }
}